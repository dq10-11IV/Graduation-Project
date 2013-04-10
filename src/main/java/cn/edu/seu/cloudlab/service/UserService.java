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
		UserEntity userEntity = null;
		try {
			userEntity = userDao.getUserByUsernameAndPWD(username, password);
		} catch(Exception ex) {
			logger.error("Exception in userDao.getUserByUsernameAndPWD: ", ex);
			return null;
		}
		if(userEntity == null) {
			return null;
		} else {
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			return user;
		}
	}
	
	public List<ProductRecommendDto> getUserRecommendProducts(int userId) {
		String recommendString = null; 
		try {
			recommendString = userDao.getUserRecommends(userId);
		} catch(Exception ex) {
			logger.error("Exception in userDao.getUserRecommends: ", ex);
			return null;
		}
		
		if(StringUtils.isEmpty(recommendString)) {
			return null;
		} else {
			List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
			try {
				String[] recommendItems = recommendString.split("\\|");
				for(String recommendItem : recommendItems) {
					String[] items = recommendItem.split(",");
					int productId = Integer.parseInt(items[0]);
					int recommendValue = Integer.parseInt(items[1]);
					ProductDto product = productService.getProduct(productId);
					if(product != null) {
						ProductRecommendDto productRecommend = new ProductRecommendDto();
						productRecommend.setProduct(product);
						productRecommend.setRecommendValue(recommendValue);
						resultList.add(productRecommend);
					} 
				}
				return resultList;
			} catch(Exception ex) {
				logger.error("Exception in processing recommend result: ", ex);
				return null;
			}
		} 
		
	}
	
	public List<Integer> getSimilarUserIds(int userId) {
		String similarUserStr = null;
		try {
			similarUserStr = userDao.getSimilarUsers(userId);
		} catch(Exception ex) {
			logger.error("Exception in userDao.getSimilarUsers: ", ex);
			return null;
		}
		if(StringUtils.isEmpty(similarUserStr)) {
			return null;
		} else {
			List<Integer> similarUserIds = new ArrayList<Integer>();
			try {
				String[] similarUsers = similarUserStr.split("\\|");
				for(String similarUser : similarUsers) {
					int _userId = Integer.parseInt(similarUser);
					similarUserIds.add(_userId);
				}
				return similarUserIds;
			} catch(Exception ex) {
				logger.error("Exception in processing similar use result: ", ex);
				return null;
			}
		}
	}
	
	public List<ProductDto> getUserRecommendProductsFromLogs(int userId) {
		Map<Integer,ProductCount> productCounttMap = new HashMap<Integer, ProductCount>();
		List<Integer> similarUsers = this.getSimilarUserIds(userId);
		for(Integer _userId : similarUsers) {
			List<Integer> productIds = logService.getRecentlyProductIds(_userId);
			for(Integer productId : productIds) {
				if(!productCounttMap.containsKey(productId)) {
					ProductCount pc = new ProductCount();
					pc.setProductId(productId);
					pc.setCount(1);
					productCounttMap.put(productId, pc);
				} else {
					ProductCount pc = productCounttMap.get(productId);
					pc.setCount(pc.getCount() + 1);
				}
			}
		}
		List<ProductCount> productCountList = new ArrayList<ProductCount>(productCounttMap.values());
		Collections.sort(productCountList, new ProductCountComparator());
		List<ProductDto> resultList = new ArrayList<ProductDto>();
		for(int i = 0; i < productCountList.size() && i < 20; i ++) {
			ProductCount pc = productCountList.get(i);
			int _productId = pc.getProductId();
			ProductDto product = productService.getProduct(_productId);
			if(product != null) {
				resultList.add(product);
			}
		}
		return resultList.size() > 0 ? resultList : null;
	}
}
