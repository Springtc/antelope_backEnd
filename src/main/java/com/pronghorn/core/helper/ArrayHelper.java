package com.pronghorn.core.helper;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/** 数组、集合处理工具类 
 * 
 * @author 王磊
 * @version 2015年8月28日 15:47:54 王磊 补充头信息
 * @version 2015年10月11日 16:29:05 王磊 增加join方法
 * @version 2015年10月26日 19:47:47 王磊 补充判断语句后的跨号 */
@SuppressWarnings("rawtypes")
public class ArrayHelper {

	private ArrayHelper() {}

	/** 字符串变长参数转List集合 */
	public static List<String> boxList(String... obj) {
		List<String> list = Lists.newArrayList();

		for (String string : obj) {
			list.add(string);
		}

		return list;
	}
	
	/** 去除数组中不需要的字符串 */
	public static String[] remove(String[] a, String s) {

		if (StringHelper.isEmpty(s)){
			return a;
		}

		List<String> list = boxList(a);
		
		list.remove(s);
		return list.toArray(new String[list.size()]);
	}

	/** 去除数组中不需要的字符串数组 */
	public static String[] remove(String[] a, String[] s) {

		if (s == null || s.length == 0){
			return a;
		}

		List<String> list = boxList(a);

		list.removeAll(Arrays.asList(s));
		return list.toArray(new String[list.size()]);
	}

	/** 去除数组中不需要的字符串集合 */
	public static String[] remove(String[] a, List<String> s) {

		if (s == null || s.size() == 0){
			return a;
		}

		List<String> list = boxList(a);
		
		list.removeAll(s);
		return list.toArray(new String[list.size()]);
	}

	/** 给已经定义好的数组添加内容 */
	public static String[] add(String[] a, String s) {

		if (StringHelper.isEmpty(s)){
			return a;
		}

		List<String> list = ArrayHelper.boxList(a);
		
		if (list.indexOf(s) == -1) {
			list.add(s);
		}
		return list.toArray(new String[list.size()]);
	}

	/** 将','号链接字符串分割成字符串集合 */
	public static List<String> split(String s) {
		return split(s, ",");
	}
	
	/** 将任意符号链接字符串分割成字符串集合(去重复) */
	public static List<String> split(String s, String split) {
		return split(s, split, true);
	}
	
	/** 获取对象集合中的特定属性拼接成字符串 */
	public static String join(List list, String property, String join){
		String str = "";
		for (Object object : list) {
			str += ReflectionHelper.invokeGetter(object, property) + join;
		}
		
		if(str.length() != 0){
			str = str.substring(0, str.length() - 1);
		}
		
		return str;
	}
	
	/** 获取对象集合中的特定属性拼接成字符串 */
	public static String join(List<String> list, String join){
		String str = "";
		for (String string : list) {
			str += string + join;
		}
		
		if(str.length() != 0){
			str = str.substring(0, str.length() - 1);
		}
		
		return str;
	}
	
	/** 根据分号分割字符串，取第一个分号之前的部分 */
	public static String getFirstPartBySemicolon(String str) {
		List<String> list = split(str, ";");
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/** 将任意符号链接字符串分割成字符串集合 */
	public static List<String> split(String s, String split, Boolean repeat) {
		List<String> list = Lists.newArrayList();

		if (StringHelper.isEmpty(s)){
			return list;
		}

		String[] elements = StringHelper.split(s, split);
		if ((elements != null) && (elements.length > 0)) {
			for (String element : elements) {
				if(repeat){
					if (!StringHelper.isEmpty(element.trim()) && list.indexOf(element.trim()) == -1) {
						list.add(element.trim());
					}
				}else{
					list.add(element.trim());
				}
				
			}
		}
		return list;
	}
	
	/** 将任意符号链接字符串分割成字符串集合 */
	public static List<Long> splitToLong(String s, String split, Boolean repeat) {
		List<Long> list = Lists.newArrayList();

		if (StringHelper.isEmpty(s)){
			return list;
		}

		String[] elements = StringHelper.split(s, split);
		if ((elements != null) && (elements.length > 0)) {
			for (String element : elements) {
				if(repeat){
					if(StringHelper.isNotBlank(element.trim())){
						Long item = Long.parseLong(element.trim());
						if(!list.contains(item)){
							list.add(item);
						}
					}
				}else{
					list.add(Long.parseLong(element.trim()));
				}
				
			}
		}
		return list;
	}
	
	/** 如果emp1中有emp2的 去掉 */
	public static String exclude(String emp1, String emp2) {
		if (StringHelper.isNotBlank(emp1)){
			emp1 = emp1.replace(";", ",");
		}

		if (StringHelper.isNotBlank(emp2)){
			emp2 = emp2.replace(";", ",");
		}

		if (StringHelper.isEmpty(emp2) || StringHelper.isEmpty(emp1)){
			return emp1;
		}

		List<String> emp1s = split(emp1);
		List<String> emp2s = split(emp2);
		for (String emp : emp2s) {
			if (emp1s.indexOf(emp) != -1){
				emp1s.remove(emp);
			}
		}

		return StringHelper.join(emp1s, ",");
	}

}
