/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.seu.cloudlab.dto.UserDto;
import cn.edu.seu.cloudlab.interceptor.LoginInterceptor;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author iHome
 *
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	private Map<String, Object> session;
	
	protected UserDto user;
	
	public abstract String doExcute();

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		user = (UserDto) session.get(LoginInterceptor.USER_SESSION_KEY);
		String result = null;
		try {
			result = doExcute();
		} catch (Exception ex) {
			result = ERROR;
			logger.error("Exception in BaseAction.excute: ", ex);
		}
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * @return the user
	 */
	public UserDto getUser() {
		return user;
	}

}
