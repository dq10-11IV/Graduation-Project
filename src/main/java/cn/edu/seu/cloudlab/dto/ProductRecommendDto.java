/**
 * 
 */
package cn.edu.seu.cloudlab.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author iHome
 *
 */
public class ProductRecommendDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductDto product;
	private boolean hasRecommendValue;
	private BigDecimal recommendValue;
	
	/**
	 * @return the product
	 */
	public ProductDto getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDto product) {
		this.product = product;
	}

	/**
	 * @return the hasRecommendValue
	 */
	public boolean getHasRecommendValue() {
		return hasRecommendValue;
	}
	/**
	 * @param hasRecommendValue the hasRecommendValue to set
	 */
	public void setHasRecommendValue(boolean hasRecommendValue) {
		this.hasRecommendValue = hasRecommendValue;
	}
	/**
	 * @return the recommendValue
	 */
	public BigDecimal getRecommendValue() {
		return recommendValue;
	}
	/**
	 * @param recommendValue the recommendValue to set
	 */
	public void setRecommendValue(BigDecimal recommendValue) {
		this.recommendValue = recommendValue;
	}
}
