/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.cloudlab.dao.UserDao;
import cn.edu.seu.cloudlab.dto.ProductDto;
import cn.edu.seu.cloudlab.dto.ProductRecommendDto;
import cn.edu.seu.cloudlab.dto.UserDto;
import cn.edu.seu.cloudlab.entity.UserEntity;
import cn.edu.seu.cloudlab.util.ProductRecommendComparator;

/**
 * @author iHome
 *
 */

@Service
public class UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LogService logService;
	
	public UserDto login(String username, String password) {
		try {
			UserEntity userEntity = userDao.getUserByUsernameAndPWD(username, password);
			if(userEntity == null) {
				return null;
			}
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			return user;
		} catch(Exception ex) {
			logger.error("Exception in UserService.login, ex: ", ex);
			return null;
		}
	}
	
	public List<ProductRecommendDto> getUserRecommendProducts(String userId) {
		try {
			String recommendString = userDao.getUserRecommends(userId);
			if(StringUtils.isEmpty(recommendString)) {
				return null;
			} 
			List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
			String[] recommendItems = recommendString.split("\\|");
			List<String> productIdList = new ArrayList<String>();
			Map<String, String> recommendValueMap = new HashMap<String, String>();
			for(String recommendItem : recommendItems) {
				String[] items = recommendItem.split(",");
				String productId = items[0];
				String recommendValue = items[1];
				productIdList.add(productId);
				recommendValueMap.put(productId, recommendValue);
			}
			List<ProductDto> productList = productService.batchGetProducts(productIdList);
			if(productList == null) {
				return null;
			}
			for (ProductDto productDto : productList) {
				ProductRecommendDto productRecommendDto = new ProductRecommendDto();
				productRecommendDto.setProduct(productDto);
				String recommendValue = recommendValueMap.get(productDto.getId());
				productRecommendDto.setRecommendValue(new BigDecimal(recommendValue));
				productRecommendDto.setHasRecommendValue(true);
//				productRecommendDto.setShouldShowRecommendValue(true);
				resultList.add(productRecommendDto);
			}
			Collections.sort(resultList, new ProductRecommendComparator());
			return resultList;
		} catch(Exception ex) {
			logger.error("Exception in UserService.getUserRecommendProduct, ex: ", ex);
			return null;
		}	
	}
	
	public List<String> getSimilarUserIds(String theUserId) {
		try {
			String similarUserStr = userDao.getSimilarUsers(theUserId);
			if(StringUtils.isEmpty(similarUserStr)) {
				return null;
			} 
			List<String> resultList = new ArrayList<String>();
			String[] similarUsers = similarUserStr.split("\\|");
			for(String similarUserId : similarUsers) {
				resultList.add(similarUserId);
			}
			return resultList;
		} catch(Exception ex) {
			logger.error("Exception in UserService.getSimilarUserIds, ex: ", ex);
			return null;
		}
	}
	
	public List<ProductRecommendDto> getUserRecommendProductsFromLogs(String theUserId) {
		try {
			Map<String,Integer> productCountMap = new HashMap<String, Integer>();
			List<String> similarUsers = this.getSimilarUserIds(theUserId);
			if(similarUsers == null) {
				return null;
			}
			for(String userId : similarUsers) {
				List<String> productIds = logService.getRecentlyProductIds(userId);
				if (productIds == null) {
					continue;
				}
				for(String productId : productIds) {
					if(!productCountMap.containsKey(productId)) {
						productCountMap.put(productId, 1);
					} else {
						productCountMap.put(productId, productCountMap.get(productId) + 1);
					}
				}
			}
			if(productCountMap.isEmpty()) {
				return null;
			}
			List<String> productIdList = new ArrayList<String>(productCountMap.keySet());
			List<ProductDto> productList = productService.batchGetProducts(productIdList);
			if (productList == null) {
				return null;
			}
			List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
			for (ProductDto productDto : productList) {
				ProductRecommendDto productRecommendDto = new ProductRecommendDto();
				productRecommendDto.setProduct(productDto);
				productRecommendDto.setHasRecommendValue(true);
				productRecommendDto.setRecommendValue(new BigDecimal(productCountMap.get(productDto.getId())));
				resultList.add(productRecommendDto);
			}
			Collections.sort(resultList, new ProductRecommendComparator());
			return resultList;
		}catch(Exception ex) {
			logger.error("Exception in UserService.getUserRecommendProductsFromLogs, ex: ", ex);
			return null;
		}
	}
}
