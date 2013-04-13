/**
 * 
 */
package cn.edu.seu.cloudlab.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.UserDto;
import cn.edu.seu.cloudlab.service.LogService;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author iHome
 *
 */
public class UserLogInterceptor extends AbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UserLogInterceptor.class);
	
	@Autowired
	private LogService logService;
	
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			Map<String, Object> session = invocation.getInvocationContext().getSession();
			UserDto user = (UserDto) session.get(LoginInterceptor.USER_SESSION_KEY);
			String productId = request.getParameter("productId");
			String userId = user.getId();
			if(user!= null && StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(productId)) {
				logService.addUserLog(userId, productId);
			}
		} catch(Exception ex) {
			logger.error("Exception in UserLogInterceptor.intercept, ex: ", ex);
		}
		return invocation.invoke();
	}
}
