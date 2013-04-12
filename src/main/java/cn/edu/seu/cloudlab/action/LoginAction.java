/**
 * 
 */
package cn.edu.seu.cloudlab.action;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.UserDto;
import cn.edu.seu.cloudlab.interceptor.LoginInterceptor;
import cn.edu.seu.cloudlab.service.UserService;

import com.opensymphony.xwork2.ActionSupport;



/**
 * @author iHome
 *
 */
public class LoginAction extends ActionSupport implements ServletResponseAware, SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
	
	private final String INPUT = "input";
	
	@Autowired
	private UserService userService;
	
    private HttpServletResponse response;  
	private Map<String, Object> session;
    
	private String action = "normal";
	
	private String username;
	private String password;
	private boolean rememberMe;
	
	private String message;

	
	@Override
	public String execute() throws Exception {
		try {
			if(action.equals("login")) {
				if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
					message = "Please enter your username and password.";
					return INPUT;
				}
				UserDto user = userService.login(username, password);
				if (user == null) {
					message = "Something wrong with your username or password, please check again!";
					return INPUT;
				} else {
					if(rememberMe) {
						 Cookie cookie = new Cookie(LoginInterceptor.COOKIE_REMEMBERME_KEY, username + "==" + password);
			             cookie.setMaxAge(60 * 60 * 24 * 14);  
			             response.addCookie(cookie);
					}
					session.put(LoginInterceptor.USER_SESSION_KEY, user);
					return SUCCESS;
				}
				
			} else {
				return INPUT;
			}
		} catch(Exception ex) {
			logger.error("Exception in LoginAction.execute, ex: ", ex);
			return ERROR;
		}
	}


	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
