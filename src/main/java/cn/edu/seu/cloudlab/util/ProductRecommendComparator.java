/**
 * 
 */
package cn.edu.seu.cloudlab.util;

import java.util.Comparator;

import cn.edu.seu.cloudlab.dto.ProductRecommendDto;

/**
 * @author snow
 *
 */
public class ProductRecommendComparator implements Comparator<ProductRecommendDto> {

	@Override
	public int compare(ProductRecommendDto productRecommend1, ProductRecommendDto productRecommend2) {
		if (productRecommend1.getHasRecommendValue() && productRecommend2.getHasRecommendValue()) {
			return -(productRecommend1.getRecommendValue().compareTo(productRecommend2.getRecommendValue()));
		} else {
			return 0;
		}
	}

}
