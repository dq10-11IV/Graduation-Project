/**
 * 
 */
package cn.edu.seu.cloudlab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
	public ProductEntity getProduct(@Param("productId") String productId);
	
	@Select("SELECT recommends FROM product_recommends WHERE product_id = #{productId} LIMIT 1")
	public String getProductRecommends (@Param("productId") String productId); 
	
	@Select("SELECT p.id, p.product_name, p.product_index1, p.product_index2 FROM products p JOIN product_hots h ON p.id = product_id WHERE p.product_index1 = #{index1} ORDER BY h.hot_degree DESC LIMIT #{topN}")
	@Results(value={
			@Result(property="productName", column="product_name"),
			@Result(property="productIndex1", column="product_index1"),
			@Result(property="productIndex2", column="product_index2")
	})
	public List<ProductEntity> getProductRecommendsByIndex1(@Param("index1") String index1, 
													        @Param("topN")   int topN);
	
	@Select("SELECT id, product_name, product_index1, product_index2 FROM products WHERE FIND_IN_SET(id, #{productIds})")
	@Results(value={
			@Result(property="productName", column="product_name"),
			@Result(property="productIndex1", column="product_index1"),
			@Result(property="productIndex2", column="product_index2")
	})
	public List<ProductEntity> batchGetProduct(@Param("productIds") String productIds);
}
