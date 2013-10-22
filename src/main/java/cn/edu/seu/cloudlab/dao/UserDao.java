/**
 * 
 */
package cn.edu.seu.cloudlab.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.edu.seu.cloudlab.entity.UserEntity;

/**
 * @author iHome
 *
 */
public interface UserDao {
	@Select("SELECT id, username, password FROM users WHERE id = #{userId} LIMIT 1")
	public UserEntity getUser(@Param("userId") String userId);
	
	@Select("SELECT id, username, password FROM users WHERE username = #{username} AND password = #{password} LIMIT 1")
	public UserEntity getUserByUsernameAndPWD(@Param("username") String username,
							  @Param("password") String password);
	
	@Select("SELECT similar_users FROM user_similar_users WHERE user_id = #{userId}")
	public String getSimilarUsers(@Param("userId") String userId);
	
	@Select("SELECT recommends FROM user_recommends WHERE user_id = #{userId}")
	public String getUserRecommends(@Param("userId") String userId);
}
