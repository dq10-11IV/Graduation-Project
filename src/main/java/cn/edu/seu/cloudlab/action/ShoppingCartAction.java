/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.CartItemDto;
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

	/* (non-Javadoc)
	 * @see cn.edu.seu.cloudlab.action.BaseAction#doExecute()
	 */
	@Override
	public String doExecute() {
		try {
			cartItemDtoList = shoppingCartService.getShoppingCart(getUser().getId());
		} catch(Exception ex) {
			logger.error("Exception in ShoppingCartAction.doExecute, ex: ", ex);
		}
		return SUCCESS;
	}

	/**
	 * @return the cartItemDtoList
	 */
	public List<CartItemDto> getCartItemDtoList() {
		return cartItemDtoList;
	}
}
