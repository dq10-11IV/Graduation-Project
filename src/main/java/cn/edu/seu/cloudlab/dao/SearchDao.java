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
public interface SearchDao {
	
	@Select("SELECT results FROM searches WHERE keyword=#{keyword} LIMIT 1")
	public String getSearchResults(@Param("keyword")String keyword);
	
}
