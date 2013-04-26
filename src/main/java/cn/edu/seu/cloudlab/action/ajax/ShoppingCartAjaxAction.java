/**
 * 
 */
package cn.edu.seu.cloudlab.action.ajax;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.service.ShoppingCartService;

/**
 * @author snow
 *
 */
public class ShoppingCartAjaxAction extends BaseAjaxAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(ShoppingCartAjaxAction.class);
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	private String action;
	private String productId;
	private String productNum;

	/* (non-Javadoc)
	 * @see cn.edu.seu.cloudlab.action.BaseAction#doExecute()
	 */
	@Override
	public void doAjaxExecute() {
		if (action == null) {
			putCode(ERROR);
			putContent("msg", "No Action.");
			return;
		}
		if (action.equals("add")) {
			addShoppingCart();
		} else if (action.equals("delete")) {
			deleteShoppingCart();
		} else {
			putCode(ERROR);
			putContent("msg", "No Mathc Action.");
			return;
		}
	}
	
	public void addShoppingCart() {
		try {
			if (StringUtils.isEmpty(productId)) {
				putCode(ERROR);
				putContent("msg", "Product Id ERROR.");
				return;
			}
			if (getUser() == null) {
				putCode(ERROR);
				putContent("msg", "Please Login.");
				return;
			}
			if (StringUtils.isEmpty(productNum)) {
				putCode(ERROR);
				putContent("msg", "Product Num ERROR.");
				return;
			}
			int theProductNum = Integer.parseInt(productNum);
			shoppingCartService.addShoppingCart(getUser().getId(), productId, theProductNum);
			putCode(SUCCESS);
			putContent("msg", "SUCCESS!");
			return;
		} catch (Exception ex) {
			logger.error("Exception in ShoppingCartAjaxAction.addShoppingCart, ex: ", ex);
			putCode(ERROR);
			putContent("msg", "Exception has occured.");
			return;
		}
	}
	
	public void deleteShoppingCart() {
		try {
			if (StringUtils.isEmpty(productId)) {
				putCode(ERROR);
				putContent("msg", "Product Id ERROR.");
				return;
			}
			if (getUser() == null) {
				putCode(ERROR);
				putContent("msg", "Please Login.");
				return;
			}
			shoppingCartService.deleteShoppingCart(getUser().getId(), productId);
			putCode(SUCCESS);
			putContent("msg", "SUCCESS!");
			return;
		} catch (Exception ex) {
			logger.error("Exception in ShoppingCartAjaxAction.deleteShoppingCart, ex: ", ex);
			putCode(ERROR);
			putContent("msg", "Exception has occured.");
			return;
		}
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @param productNum the productNum to set
	 */
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
}
