/**
 * 
 */
package cn.edu.seu.cloudlab.service;

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
import cn.edu.seu.cloudlab.util.ProductCount;
import cn.edu.seu.cloudlab.util.ProductCountComparator;

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
			} else {
				UserDto user = new UserDto();
				BeanUtils.copyProperties(userEntity, user);
				return user;
			}
		} catch(Exception ex) {
			logger.error("Exception in UserService.login, ex: ", ex);
			return null;
		}
	}
	
	public List<ProductRecommendDto> getUserRecommendProducts(int userId) {
		try {
			String recommendString = userDao.getUserRecommends(userId);
			if(StringUtils.isEmpty(recommendString)) {
				return null;
			} else {
				List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
				String[] recommendItems = recommendString.split("\\|");
				for(String recommendItem : recommendItems) {
					String[] items = recommendItem.split(",");
					int productId = Integer.parseInt(items[0]);
					int recommendValue = Integer.parseInt(items[1]);
					ProductDto product = productService.getProduct(productId);
					if(product != null) {
						ProductRecommendDto productRecommend = new ProductRecommendDto();
						productRecommend.setProduct(product);
						productRecommend.setHasRecommendValue(true);
						productRecommend.setRecommendValue(recommendValue);
						resultList.add(productRecommend);
					} 
				}
				return resultList;
			} 
		} catch(Exception ex) {
			logger.error("Exception in UserService.getUserRecommendProduct, ex: ", ex);
			return null;
		}
		
	}
	
	public List<Integer> getSimilarUserIds(int theUserId) {
		try {
			String similarUserStr = userDao.getSimilarUsers(theUserId);
			if(StringUtils.isEmpty(similarUserStr)) {
				return null;
			} else {
				List<Integer> resultList = new ArrayList<Integer>();
				String[] similarUsers = similarUserStr.split("\\|");
				for(String similarUser : similarUsers) {
					int userId = Integer.parseInt(similarUser);
					resultList.add(userId);
				}
				return resultList;
			}
		} catch(Exception ex) {
			logger.error("Exception in UserService.getSimilarUserIds, ex: ", ex);
			return null;
		}
	}
	
	public List<ProductRecommendDto> getUserRecommendProductsFromLogs(int theUserId) {
		try {
			Map<Integer,ProductCount> productCountMap = new HashMap<Integer, ProductCount>();
			List<Integer> similarUsers = this.getSimilarUserIds(theUserId);
			for(Integer userId : similarUsers) {
				List<Integer> productIds = logService.getRecentlyProductIds(userId);
				for(Integer productId : productIds) {
					if(!productCountMap.containsKey(productId)) {
						ProductCount pc = new ProductCount();
						pc.setProductId(productId);
						pc.setCount(1);
						productCountMap.put(productId, pc);
					} else {
						ProductCount pc = productCountMap.get(productId);
						pc.setCount(pc.getCount() + 1);
					}
				}
			}
			List<ProductCount> productCountList = new ArrayList<ProductCount>(productCountMap.values());
			Collections.sort(productCountList, new ProductCountComparator());
			List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
			for(int i = 0; i < productCountList.size() && i < 20; i ++) {
				ProductCount pc = productCountList.get(i);
				int productId = pc.getProductId();
				ProductDto product = productService.getProduct(productId);
				if(product != null) {
					ProductRecommendDto productRecommendDto = new ProductRecommendDto();
					productRecommendDto.setProduct(product);
					productRecommendDto.setHasRecommendValue(false);
					resultList.add(productRecommendDto);
				}
			}
			return resultList.size() > 0 ? resultList : null;
		}catch(Exception ex) {
			logger.error("Exception in UserService.getUserRecommendProductsFromLogs, ex: ", ex);
			return null;
		}
	}
}
