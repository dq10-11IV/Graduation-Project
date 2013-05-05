/**
 * 
 */
package cn.edu.seu.cloudlab.dto;

import java.io.Serializable;

/**
 * @author snow
 *
 */
public class CartItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String productName;
	private int productNum;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productNum
	 */
	public int getProductNum() {
		return productNum;
	}
	/**
	 * @param productNum the productNum to set
	 */
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
}
