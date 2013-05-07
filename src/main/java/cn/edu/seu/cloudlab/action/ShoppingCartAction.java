/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.CartItemDto;
import cn.edu.seu.cloudlab.dto.ProductRecommendDto;
import cn.edu.seu.cloudlab.service.ShoppingCartService;

/**
 * @author snow
 *
 */
public class ShoppingCartAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(ShoppingCartAction.class); 
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	private List<CartItemDto> cartItemDtoList;
	
	private List<ProductRecommendDto> productRecommends;

	/* (non-Javadoc)
	 * @see cn.edu.seu.cloudlab.action.BaseAction#doExecute()
	 */
	@Override
	public String doExecute() {
		try {
			cartItemDtoList = shoppingCartService.getShoppingCart(getUser().getId());
			loadRecommendsDriver();
			return SUCCESS;
		} catch(Exception ex) {
			logger.error("Exception in ShoppingCartAction.doExecute, ex: ", ex);
			return ERROR;
		}
	}
	
	private void loadRecommendsDriver() {
		if (cartItemDtoList != null && cartItemDtoList.size() > 0) {
			List<String> productIdList = new ArrayList<String>();
			for (int i = 0; i < cartItemDtoList.size() && i < 5; i++) {
				productIdList.add(cartItemDtoList.get(i).getId());
			}
			loadRecommends(productIdList);
		}
	}
	
	private void loadRecommends(List<String> productIds) {
		productRecommends = shoppingCartService.getProductRecommendsFromShoppingCart(productIds);
		if (productRecommends == null) {
			if (productIds.size() == 1) {
				return;
			} else {
				loadRecommends(productIds.subList(0, productIds.size() - 2));
			}
		} else {
			return;
		}
	}

	/**
	 * @return the cartItemDtoList
	 */
	public List<CartItemDto> getCartItemDtoList() {
		return cartItemDtoList;
	}

	/**
	 * @return the productRecommends
	 */
	public List<ProductRecommendDto> getProductRecommends() {
		return productRecommends;
	}
}
