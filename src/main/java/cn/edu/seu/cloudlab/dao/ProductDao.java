/**
 * 
 */
package cn.edu.seu.cloudlab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;

import cn.edu.seu.cloudlab.entity.ProductEntity;

/**
 * @author iHome
 *
 */
public interface ProductDao {
	
	@Select("SELECT id, product_name, product_index1, product_index2 FROM products WHERE id = #{productId} LIMIT 1")
	@Results(value={
			@Result(property="productName", column="product_name"),
			@Result(property="productIndex1", column="product_index1"),
			@Result(property="productIndex2", column="product_index2")
	})
	public ProductEntity getProduct(@Param("productId") int productId);
	
	@Select("SELECT recommends FROM product_recommends WHERE product_id = #{productId} LIMIT 1")
	public String getProductRecommends (@Param("productId") int productId); 
	
	@Select("SELECT id FROM products WHERE product_index1 = #{index1} AND product_index2 = #{index2}")
	public List<Integer> getProductByIndex(@Param("index1") int index1, 
										   @Param("index2") int index2);
	
	@Select("SELECT hot_degree FROM product_hots WHERE product_id = #{productId}")
	public int getProductHotDegree(@Param("productId") int productId);
}
