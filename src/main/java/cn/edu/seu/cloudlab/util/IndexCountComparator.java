/**
 * 
 */
package cn.edu.seu.cloudlab.util;

import java.util.Comparator;

/**
 * @author snow
 *
 */
public class IndexCountComparator implements Comparator<IndexCount> {

	@Override
	public int compare(IndexCount ic1, IndexCount ic2) {
		if(ic1.getCount() > ic2.getCount()) {
			return -1;
		} else if(ic1.getCount() < ic2.getCount()) {
			return 1;
		} else {
			return 0;
		}
	}

}
