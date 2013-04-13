/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.cloudlab.dao.ProductDao;
import cn.edu.seu.cloudlab.dto.ProductDto;
import cn.edu.seu.cloudlab.dto.ProductRecommendDto;
import cn.edu.seu.cloudlab.entity.ProductEntity;

/**
 * @author iHome
 *
 */
@Service
public class ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDao productDao;
	
	public ProductDto getProduct(String productId) {
		try {
			ProductEntity productEntity = productDao.getProduct(productId);
			if(productEntity == null) {
				return null;
			} else {
				ProductDto product = new ProductDto();
				BeanUtils.copyProperties(productEntity, product);
				return product;
			}
		} catch(Exception ex) {
			logger.error("Exception in ProductService.getProduct, ex: ", ex);
			return null;
		}
	}
	
	public List<ProductDto> batchGetProducts(List<String> productIdList) {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			for(String productId : productIdList) {
				stringBuilder.append(productId);
				stringBuilder.append(",");
			}
			if(stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
				stringBuilder.setLength(stringBuilder.length() - 1);
			}
			String productIds = stringBuilder.toString();
			List<ProductEntity> entities = productDao.batchGetProduct(productIds);
			if (entities == null || entities.size() == 0) {
				return null;
			}
			List<ProductDto> resultList = new ArrayList<ProductDto>();
			for (ProductEntity entity : entities) {
				if (entity != null) {
					ProductDto productDto = new ProductDto();
					BeanUtils.copyProperties(entity, productDto);
					resultList.add(productDto);
				}
			}
			return resultList;
		} catch(Exception ex) {
			logger.error("Exception in ProductService.batchGetProducts, ex: ", ex);
			return null;
		}
	}
	
	public List<ProductRecommendDto> getProductRecommendProducts(String theProductId) {
		try {
			String recommendString = productDao.getProductRecommends(theProductId);
			if(StringUtils.isEmpty(recommendString)) {
				return null;
			} else {
				List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
				String[] recommendItems = recommendString.split("\\|");
				StringBuilder productIdListBuilder = new StringBuilder();
				Map<String, String> recommendValueMap = new HashMap<String, String>(); 
				for(String recommendItem : recommendItems) {
					String[] items = recommendItem.split(",");
					String productId = items[0];
					String recommendValue = items[1];
					productIdListBuilder.append(productId);
					productIdListBuilder.append(",");
					recommendValueMap.put(productId, recommendValue);
				}
				if(productIdListBuilder.charAt(productIdListBuilder.length() - 1) == ',') {
					productIdListBuilder.setLength(productIdListBuilder.length() - 1);
				}
				List<ProductEntity> productList = productDao.batchGetProduct(productIdListBuilder.toString());
				if (productList == null || productList.size() == 0) {
					return null;
				}
				for(ProductEntity entity : productList) {
					if(entity != null) {
						ProductDto product = new ProductDto();
						BeanUtils.copyProperties(entity, product);
						String recommendValue = recommendValueMap.get(product.getId());
						ProductRecommendDto productRecommendDto = new ProductRecommendDto();
						productRecommendDto.setProduct(product);
						productRecommendDto.setRecommendValue(new BigDecimal(recommendValue));
						productRecommendDto.setHasRecommendValue(true);
						resultList.add(productRecommendDto);
					}
				}
				return resultList;
			}
		} catch(Exception ex) {
			logger.error("Exception in ProductService.getProductRecommendProducts, ex: " ,ex);
			return null;
		}
	} 
	public List<ProductRecommendDto> getProductRecommendProductsByIndex1(String index1, int topN) {
		try {
			List<ProductEntity> productEntityList = productDao.getProductRecommendsByIndex1(index1, topN);
			if(productEntityList == null) {
				return null;
			} else {
				List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
				for(ProductEntity entity : productEntityList) {
					if (entity != null) {
						ProductDto productDto = new ProductDto();
						BeanUtils.copyProperties(entity, productDto);
						ProductRecommendDto productRecommendDto = new ProductRecommendDto();
						productRecommendDto.setProduct(productDto);
						productRecommendDto.setHasRecommendValue(false);
						resultList.add(productRecommendDto);
					}
				}
				return resultList;
	 		}
		} catch(Exception ex) {
			logger.error("Exception in ProductService.getProductRecommendProductsByIndex1, ex: ", ex);
			return null;
		}
	}
}
