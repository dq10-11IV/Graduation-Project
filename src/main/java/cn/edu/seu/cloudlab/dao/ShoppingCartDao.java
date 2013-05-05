/**
 * 
 */
package cn.edu.seu.cloudlab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.edu.seu.cloudlab.entity.CartItemEntity;

/**
 * @author snow
 *
 */
public interface ShoppingCartDao {
	
	@Select("SELECT p.id, p.product_name, sc.product_num FROM shopping_carts sc JOIN products p ON sc.product_id = p.id WHERE user_id = #{userId}")
	@Results(value={
			@Result(property="productName", column="product_name"),
			@Result(property="productNum", column="product_num"),
	})
	public List<CartItemEntity> getShoppingCart(@Param("userId") String userId);
	
	@Insert("INSERT INTO shopping_carts(user_id, product_id, add_time, product_num) VALUES (#{userId}, #{productId}, NOW(), #{productNum}) ON DUPLICATE KEY UPDATE product_num = product_num + #{productNum}, add_time = NOW()")
	public void insertShoppingCart(@Param("userId") String userId, 
								   @Param("productId") String productId, 
								   @Param("productNum") int productNum);
	
	@Delete("DELETE FROM shopping_carts WHERE user_id = #{userId} AND product_id = #{productId}")
	public void deleteShoppingCart(@Param("userId") String userId, 
								   @Param("productId") String productId);
	
}
