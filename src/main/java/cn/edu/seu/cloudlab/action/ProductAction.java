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
import cn.edu.seu.cloudlab.service.LogService;
import cn.edu.seu.cloudlab.service.ProductService;

/**
 * @author iHome
 *
 */
public class ProductAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(ProductAction.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LogService logService;
	
	private int productId;
	
	private ProductDto currentProduct;
	private List<ProductRecommendDto> productRecommends;

	/* (non-Javadoc)
	 * @see cn.edu.seu.cloudlab.action.BaseAction#doExcute()
	 */
	@Override
	public String doExcute() {
		try {
			currentProduct = productService.getProduct(productId);
		} catch(Exception ex) {
			logger.error("Exception in productService.getProduct: ", ex);
		}
		
		try {
			productRecommends = productService.getProductRecommendProducts(productId);
		} catch(Exception ex) {
			logger.error("Exception in productService.getProductRecommendProducts: ", ex);
		}
		return SUCCESS;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the currentProduct
	 */
	public ProductDto getCurrentProduct() {
		return currentProduct;
	}

	/**
	 * @return the productRecommends
	 */
	public List<ProductRecommendDto> getProductRecommends() {
		return productRecommends;
	}

}
