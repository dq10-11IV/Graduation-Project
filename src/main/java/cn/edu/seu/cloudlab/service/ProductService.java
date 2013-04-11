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
		ProductEntity productEntity = null;
		try {
			productEntity = productDao.getProduct(productId);
		} catch(Exception ex) {
			logger.error("Exception in productDao.getProduct: ", ex);
			return null;
		}
		if(productEntity == null) {
			return null;
		} else {
			ProductDto product = new ProductDto();
			BeanUtils.copyProperties(productEntity, product);
			return product;
		}
	}
	
	public List<ProductRecommendDto> getProductRecommendProducts(int productId) {
		String recommendString = null; 
		try {
			recommendString = productDao.getProductRecommends(productId);
		} catch(Exception ex) {
			logger.error("Exception in productDao.getProductRecommends: ", ex);
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
					int _productId = Integer.parseInt(items[0]);
					int recommendValue = Integer.parseInt(items[1]);
					ProductDto product = this.getProduct(_productId);
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
	public List<ProductDto> getProductRecommendProductsByIndex1(int index1, int topN) {
		List<ProductEntity> productEntityList = null;
		try {
			productEntityList = productDao.getProductRecommendsByIndex1(index1, topN);
		} catch(Exception ex) {
			logger.error("Exception in productDao.getProductRecommendsByIndex1: ", ex);
			return null;
		}
		if(productEntityList == null) {
			return null;
		} else {
			List<ProductDto> resultList = new ArrayList<ProductDto>();
			for(ProductEntity entity : productEntityList) {
				if (entity != null) {
					ProductDto productDto = new ProductDto();
					BeanUtils.copyProperties(entity, productDto);
					resultList.add(productDto);
				}
			}
			return resultList;
 		}
		
	}
}
