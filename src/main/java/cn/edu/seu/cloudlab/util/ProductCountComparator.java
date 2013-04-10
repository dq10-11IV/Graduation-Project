/**
 * 
 */
package cn.edu.seu.cloudlab.util;

import java.util.Comparator;

/**
 * @author iHome
 *
 */
public class ProductCountComparator implements Comparator<ProductCount> {

	@Override
	public int compare(ProductCount pc1, ProductCount pc2) {
		if(pc1.getCount() > pc2.getCount()) {
			return -1;
		} else if(pc1.getCount() < pc2.getCount()) {
			return 1;
		} else {
			return 0;
		}
	}

}
