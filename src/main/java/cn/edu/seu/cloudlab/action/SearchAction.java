/**
 * 
 */
package cn.edu.seu.cloudlab.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.seu.cloudlab.dto.ProductDto;
import cn.edu.seu.cloudlab.dto.ProductRecommendDto;
import cn.edu.seu.cloudlab.service.ProductService;
import cn.edu.seu.cloudlab.service.SearchService;
import cn.edu.seu.cloudlab.util.IndexCount;
import cn.edu.seu.cloudlab.util.IndexCountComparator;

/**
 * @author snow
 *
 */
public class SearchAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(SearchAction.class);
	
	private static final int TOP_N = 10;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ProductService productService;
	
	private List<ProductDto> searchResult;
	
	private List<ProductRecommendDto> productRecommendsFromSearch;
	
	private String q = null;

	/* (non-Javadoc)
	 * @see cn.edu.seu.cloudlab.action.BaseAction#doExcute()
	 */
	@Override
	public String doExecute() {
		try {
			if(StringUtils.isNotEmpty(q)) {
				searchResult = searchService.getSearchResult(q);
			}
			if(searchResult != null && searchResult.size() > 0) {
				Map<String, Integer> index1CountMap = new HashMap<String, Integer>();
				for(ProductDto productDto : searchResult) {
					if(!index1CountMap.containsKey(productDto.getProductIndex1())) {
						index1CountMap.put(productDto.getProductIndex1(), 1);
					} else {
						index1CountMap.put(productDto.getProductIndex1(), index1CountMap.get(productDto.getProductIndex1()) + 1);
					}
				}
				Set<Entry<String, Integer>> entries = index1CountMap.entrySet();
				Iterator<Entry<String, Integer>> iterator = entries.iterator();
				List<IndexCount> indexCountList = new ArrayList<IndexCount>();
				while(iterator.hasNext()) {
					Entry<String, Integer> entry = iterator.next();
					if(entry.getValue() > 1) {
						IndexCount indexCount = new IndexCount();
						indexCount.setIndex1(entry.getKey());
						indexCount.setCount(entry.getValue());
						indexCountList.add(indexCount);
					}
				}
				Collections.sort(indexCountList, new IndexCountComparator());
				for(IndexCount ic : indexCountList) {
					List<ProductRecommendDto> resultList = 
							productService.getProductRecommendProductsByIndex1(ic.getIndex1(), TOP_N);
					if(resultList != null && resultList.size() > 0) {
						if(productRecommendsFromSearch == null) {
							productRecommendsFromSearch = new ArrayList<ProductRecommendDto>();
						}
						productRecommendsFromSearch.addAll(resultList);
					}
				}
			}
			return SUCCESS;
		} catch(Exception ex) {
			logger.error("Exception in SearchAction.doExecute,ex: ", ex);
			return ERROR;
		}
	}

	/**
	 * @param q the q to set
	 */
	public void setQ(String q) {
		this.q = q;
	}

	/**
	 * @return the searchResult
	 */
	public List<ProductDto> getSearchResult() {
		return searchResult;
	}

	/**
	 * @return the productRecommendsFromSearch
	 */
	public List<ProductRecommendDto> getProductRecommendsFromSearch() {
		return productRecommendsFromSearch;
	}

}
