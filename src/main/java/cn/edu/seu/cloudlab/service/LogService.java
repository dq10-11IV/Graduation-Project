/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.cloudlab.dao.UserLogDao;

/**
 * @author iHome
 *
 */
@Service
public class LogService {
	
	private static Logger logger = LoggerFactory.getLogger(LogService.class);
	@Autowired
	private UserLogDao userLogDao;
	
	public void addUserLog(int userId, int productId) {
		try {
			userLogDao.insertUserLog(userId, productId);
		} catch(Exception ex) {
			logger.error("Exception in userLogDao.insertUserLog ex: ", ex);
		}
	}
	
	public List<Integer> getRecentlyProductIds(int userId) {
		List<Integer> resultList = null;
		try {
			resultList = userLogDao.getRecentProductIds(userId);
		} catch(Exception ex) {
			logger.error("Exception in userLogDao.getRecentProductIds ex: ", ex);
		}
		return resultList;
	}
}
