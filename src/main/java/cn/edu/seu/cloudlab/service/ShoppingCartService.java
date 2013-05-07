/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.cloudlab.dao.ShoppingCartDao;
import cn.edu.seu.cloudlab.dao.ShoppingCartRuleDao;
import cn.edu.seu.cloudlab.dto.CartItemDto;
import cn.edu.seu.cloudlab.dto.ProductDto;
import cn.edu.seu.cloudlab.dto.ProductRecommendDto;
import cn.edu.seu.cloudlab.entity.CartItemEntity;

/**
 * @author snow
 *
 */

@Service
public class ShoppingCartService {
	private static Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Autowired
	private ShoppingCartRuleDao shoppingCartRuleDao;
	
	@Autowired
	private ProductService productService;

	public List<CartItemDto> getShoppingCart(String userId) {
		try {
			List<CartItemEntity> cartItemList = shoppingCartDao.getShoppingCart(userId);
			if (cartItemList == null || cartItemList.size() == 0) {
				return null;
			}
			List<CartItemDto> resultList = new ArrayList<CartItemDto>();
			for (CartItemEntity entity : cartItemList) {
				CartItemDto cartItem = new CartItemDto();
				BeanUtils.copyProperties(entity, cartItem);
				resultList.add(cartItem);
			}
			return resultList;
		} catch (Exception ex) {
			logger.error("Exception in ShoppingCartService.getShoppingCart, ex: ", ex);
			return null;
		}
	}
	
	public void addShoppingCart(String userId, String productId, int productNum) {
		try {
			shoppingCartDao.insertShoppingCart(userId, productId, productNum);
		} catch (Exception ex) {
			logger.error("Exception in ShoppingCartService.addShoppingCart, ex: ", ex);
		}
	}
	
	public void deleteShoppingCart(String userId, String productId) {
		try {
			shoppingCartDao.deleteShoppingCart(userId, productId);
		} catch(Exception ex) {
			logger.error("Exception in ShoppingCartService.deleteShoppingCart, ex: ", ex);
		}
	}
	
	public List<ProductRecommendDto> getProductRecommendsFromShoppingCart(List<String> _productIds) {
		try {
			StringBuilder queryKeyBuilder = new StringBuilder();
			if (_productIds != null && _productIds.size() > 0) {
				queryKeyBuilder.append("[");
				for (int i = 0; i < _productIds.size(); i++) {
					queryKeyBuilder.append(_productIds.get(i));
					if (i != _productIds.size() - 1) {
						queryKeyBuilder.append(", ");
					}
				}
				queryKeyBuilder.append("]");
				String valueString = shoppingCartRuleDao.getShoppingCartRule(queryKeyBuilder.toString());
				if (StringUtils.isEmpty(valueString)) {
					return null;
				}
				String idString = valueString.substring(1, valueString.length() - 1);
				String[] productIds = idString.split(",");
				List<String> productIdList = Arrays.asList(productIds);
				List<ProductDto> productList = productService.batchGetProducts(productIdList);
				if (productIdList == null || productIdList.size() == 0) {
					return null;
				}
				List<ProductRecommendDto> resultList = new ArrayList<ProductRecommendDto>();
				for (ProductDto productDto : productList) {
					ProductRecommendDto productRecommendDto = new ProductRecommendDto();
					productRecommendDto.setProduct(productDto);
					productRecommendDto.setHasRecommendValue(false);
					productRecommendDto.setShouldShowRecommendValue(false);
					resultList.add(productRecommendDto);
				}
				return resultList;
			} else {
				return null;
			}
		} catch (Exception ex) {
			logger.error("Exception in ShoppingCartService.getProductRecommendsFromShoppingCart, ex: ", ex);
			return null;
		}
	}
}
