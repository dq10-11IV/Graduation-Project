/**
 * 
 */
package cn.edu.seu.cloudlab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author iHome
 *
 */
public interface UserLogDao {
	@Insert("INSERT INTO user_logs(user_id, product_id, add_time) VALUES (#{userId}, #{productId}, NOW())")
	public void insertUserLog(@Param("userId") String userId, 
			                  @Param("productId") String productId);
	
	@Select("SELECT product_id FROM user_logs WHERE user_id = #{userId} ORDER BY add_time DESC LIMIT 20")
	public List<String> getRecentProductIds(@Param("userId") String userId);
}
