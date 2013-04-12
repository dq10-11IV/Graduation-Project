/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public ProductDto getProduct(int productId) {
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
	
	public List<ProductRecommendDto> getProductRecommendProducts(int theProductId) {
		try {
			String recommendString = productDao.getProductRecommends(theProductId);
			if(StringUtils.isEmpty(recommendString)) {
				return null;
			} else {
				List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
				String[] recommendItems = recommendString.split("\\|");
				for(String recommendItem : recommendItems) {
					String[] items = recommendItem.split(",");
					int productId = Integer.parseInt(items[0]);
					int recommendValue = Integer.parseInt(items[1]);
					ProductDto product = this.getProduct(productId);
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
			logger.error("Exception in ProductService.getProductRecommendProducts, ex: " ,ex);
			return null;
		}
	} 
	public List<ProductRecommendDto> getProductRecommendProductsByIndex1(String index1, int topN) {
		try {
			List<ProductEntity> productEntityList = null;
			productEntityList = productDao.getProductRecommendsByIndex1(index1, topN);
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
