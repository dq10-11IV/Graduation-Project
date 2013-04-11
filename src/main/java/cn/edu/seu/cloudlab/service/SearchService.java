/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.cloudlab.dao.SearchDao;
import cn.edu.seu.cloudlab.dto.ProductDto;

/**
 * @author snow
 *
 */

@Service
public class SearchService {
	private static Logger logger = LoggerFactory.getLogger(SearchService.class);
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private ProductService productService;
	
	public List<ProductDto> getSearchResult(String keyword) {
		String searchResultString = null;
		try {
			searchResultString = searchDao.getSearchResults(keyword);
		} catch(Exception ex) {
			logger.error("Exception in searchDao.getSearchResults: ", ex);
			return null;
		}
		if(StringUtils.isEmpty(searchResultString)) {
			return null;
		} else {
			List<ProductDto> resultList = new ArrayList<ProductDto>();
			try {
				String[] items = searchResultString.split("\\|");
				for(String item : items) {
					int productId = Integer.parseInt(item);
					ProductDto product = productService.getProduct(productId);
					if(product != null) {
						resultList.add(product);
					}
				}
				return resultList;
			} catch (Exception ex) {
				logger.error("Exception in processing search result: ", ex);
				return null;
			}
		}
	}
}