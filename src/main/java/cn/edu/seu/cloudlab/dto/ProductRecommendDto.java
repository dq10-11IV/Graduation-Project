/**
 * 
 */
package cn.edu.seu.cloudlab.dto;

import java.io.Serializable;

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
	private int recommendValue;
	
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
	 * @return the recommendValue
	 */
	public int getRecommendValue() {
		return recommendValue;
	}
	/**
	 * @param recommendValue the recommendValue to set
	 */
	public void setRecommendValue(int recommendValue) {
		this.recommendValue = recommendValue;
	}
	

}
