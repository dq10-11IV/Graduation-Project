/**
 * 
 */
package cn.edu.seu.cloudlab.interceptor;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.UserDto;
import cn.edu.seu.cloudlab.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author iHome
 *
 */
public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String LOGIN = "login";
	
	public static final String USER_SESSION_KEY = "cloudlab.user";
    public static final String COOKIE_REMEMBERME_KEY="cloudlab.cookie.rememberme";  
    
    @Autowired
    private UserService userService;


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
		
		Map<String,Object> session = actionContext.getSession();
		if(session != null && session.get(USER_SESSION_KEY) != null) {
			return invocation.invoke();
		}
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {
					String value = cookie.getValue();
					if(StringUtils.isNotBlank(value)) {
						String[] spilt = value.split("==");
						String username = spilt[0];
						String password = spilt[1];
						try {
							UserDto user = userService.login(username, password);
							session.put(USER_SESSION_KEY, user);
						} catch(Exception ex) {
							return LOGIN;
						}
					} else {
						return LOGIN;
					}
					return invocation.invoke();
				}
			}
		}
		return LOGIN;
	}
}
