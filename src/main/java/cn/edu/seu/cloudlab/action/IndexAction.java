/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	private List<ProductRecommendDto> productRecommendsFromLogs;
	

	@Override
	public String doExecute() {
		try {
			productRecommends = userService.getUserRecommendProducts(user.getId());
			productRecommendsFromLogs = userService.getUserRecommendProductsFromLogs(user.getId());
			return SUCCESS;	
		} catch (Exception ex) {
			logger.error("Exception in IndexAction.doExecute, ex: ", ex);
			return ERROR;
		}
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
	public List<ProductRecommendDto> getProductRecommendsFromLogs() {
		return productRecommendsFromLogs;
	}

	
}
