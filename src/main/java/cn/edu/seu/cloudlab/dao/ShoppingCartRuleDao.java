/**
 * 
 */
package cn.edu.seu.cloudlab.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author snow
 *
 */
public interface ShoppingCartRuleDao {
	@Select("SELECT `value` FROM shopping_cart_rules WHERE `key` = #{queryKey} LIMIT 1")
	String getShoppingCartRule(@Param("queryKey") String queryKey);
}
