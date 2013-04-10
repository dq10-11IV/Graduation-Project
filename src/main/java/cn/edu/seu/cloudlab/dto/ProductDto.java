/**
 * 
 */
package cn.edu.seu.cloudlab.dto;

import java.io.Serializable;

/**
 * @author iHome
 *
 */
public class ProductDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String productName;
	private int productIndex1;
	private int productIndex2;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the productIndex1
	 */
	public int getProductIndex1() {
		return productIndex1;
	}
	/**
	 * @param productIndex1 the productIndex1 to set
	 */
	public void setProductIndex1(int productIndex1) {
		this.productIndex1 = productIndex1;
	}
	/**
	 * @return the productIndex2
	 */
	public int getProductIndex2() {
		return productIndex2;
	}
	/**
	 * @param productIndex2 the productIndex2 to set
	 */
	public void setProductIndex2(int productIndex2) {
		this.productIndex2 = productIndex2;
	}
	
}
