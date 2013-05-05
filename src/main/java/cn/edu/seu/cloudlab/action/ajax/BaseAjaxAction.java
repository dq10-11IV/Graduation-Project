/**
 * 
 */
package cn.edu.seu.cloudlab.action.ajax;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.seu.cloudlab.action.BaseAction;

/**
 * @author snow
 *
 */
public abstract class BaseAjaxAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(BaseAjaxAction.class);
	
	private BaseAjaxResult baseAjaxResult;

	/* (non-Javadoc)
	 * @see cn.edu.seu.cloudlab.action.BaseAction#doExecute()
	 */
	@Override
	public String doExecute() {
		try {
			baseAjaxResult = new BaseAjaxResult();
			doAjaxExecute();
			return SUCCESS;
		} catch (Exception ex) {
			logger.error("Exception in BaseAjaxAction.doExecute, ex: ", ex);
			putCode(ERROR);
			putContent("msg", "EXCEPTION!");
			return SUCCESS;
		}
	}
	
	protected abstract void doAjaxExecute();
	
	protected void putCode(String code) {
		baseAjaxResult.setCode(code);
	}
	
	protected void putContent(String key,String value) {
		baseAjaxResult.getContent().put(key, value);
	}
	
	
	public class BaseAjaxResult {
		private String code;
		private Map<String, String> content;
		
		public BaseAjaxResult() {
			content = new HashMap<String, String>();
		}
		
		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}
		/**
		 * @param code the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * @return the content
		 */
		public Map<String, String> getContent() {
			return content;
		}
		/**
		 * @param content the content to set
		 */
		public void setContent(Map<String, String> content) {
			this.content = content;
		}
	}


	/**
	 * @return the ajaxResult
	 */
	public BaseAjaxResult getBaseAjaxResult() {
		return baseAjaxResult;
	}
}
