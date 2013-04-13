/**
 * 
 */
package cn.edu.seu.cloudlab.service;

import java.util.Arrays;
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
		try {
			String searchResultString = searchDao.getSearchResults(keyword);
			if(StringUtils.isEmpty(searchResultString)) {
				return null;
			} 
			String[] items = searchResultString.split("\\|");
			return productService.batchGetProducts(Arrays.asList(items));
		} catch(Exception ex) {
			logger.error("Exception in SearchService.getSearchResult, ex: ", ex);
			return null;
		}
	}
}
