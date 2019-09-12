package com.pronghorn.core.helper;

import org.apache.commons.lang3.StringUtils;

/** String处理工具类
 * 
 * @author 王磊 */
public class StringHelper extends StringUtils {

	public static int lastIndexOfWithTimes(String str, String searchStr, int times){
		int index = -1;

		for (int i = 0; i < times; i++) {
			index = str.lastIndexOf(searchStr);
			str = str.substring(0, index);
		}

		return index;
	}

}
