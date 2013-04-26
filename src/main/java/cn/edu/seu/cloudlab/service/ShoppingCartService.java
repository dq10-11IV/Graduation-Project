/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.cloudlab.dao.ShoppingCartDao;
import cn.edu.seu.cloudlab.dto.CartItemDto;
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

	public List<CartItemDto> getShoppingCart(String userId) {
		try {
			List<CartItemEntity> cartItemList = shoppingCartDao.getShoppingCart(userId);
			if (cartItemList == null) {
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
}
