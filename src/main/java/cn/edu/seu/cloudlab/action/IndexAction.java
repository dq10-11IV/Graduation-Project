/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.ProductDto;
import cn.edu.seu.cloudlab.dto.ProductRecommendDto;
import cn.edu.seu.cloudlab.service.UserService;


/**
 * @author iHome
 *
 */
public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(IndexAction.class);
	
	@Autowired
	private UserService userService;
	
	private List<ProductRecommendDto> productRecommends;
	
	private List<ProductDto> productRecommendsFromLogs;
	

	@Override
	public String doExcute() {
		try {
			productRecommends = userService.getUserRecommendProducts(user.getId());
		} catch (Exception ex) {
			logger.error("Exception in userService.getUserRecommendProducts: ", ex);
		}
		try {
			productRecommendsFromLogs = userService.getUserRecommendProductsFromLogs(user.getId());
		} catch (Exception ex) {
			logger.error("Exception in userService.getRecommendFromUserLogs: ", ex);
		}
		return SUCCESS;
	}
	
	/**
	 * @return the productRecommends
	 */
	public List<ProductRecommendDto> getProductRecommends() {
		return productRecommends;
	}

	/**
	 * @return the productRecommendsFromLogs
	 */
	public List<ProductDto> getProductRecommendsFromLogs() {
		return productRecommendsFromLogs;
	}

	
}
