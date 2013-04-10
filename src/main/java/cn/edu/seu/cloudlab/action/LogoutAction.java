/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.edu.seu.cloudlab.interceptor.LoginInterceptor;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author iHome
 *
 */
public class LogoutAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession(false);  
        if (session!=null) {
            session.removeAttribute(LoginInterceptor.USER_SESSION_KEY);
        }
        Cookie[] cookies = request.getCookies();  
        if (cookies!=null) {  
            for (Cookie cookie : cookies) {  
                if (LoginInterceptor.COOKIE_REMEMBERME_KEY.equals(cookie  
                        .getName())) {  
                    cookie.setValue("");  
                    cookie.setMaxAge(0);  
                    response.addCookie(cookie);  
                    return LOGIN;  
                }  
            }  
        }  
        return LOGIN;  
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
