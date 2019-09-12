package com.pronghorn.core.helper;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Lists;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Json工具类
 *
 * @author Stone */
public class JacksonHelper {

	private static ObjectMapper objectMapper;

	public static String toJson(Object value) {
		ExceptProperties[] eps = null;
		return toJson(value, eps);
	}

	/** 将对象转换成json字符串
	 * 
	 * @param value 需要转换的对象(可以是LIST)
	 * @param exceptProperties 期望输出JSON的属性; 例:ExceptProperties.create(Tech.class, "name", "users"), ExceptProperties.create(User.class, "books")) <br>
	 *            <br>
	 *            Tech tech = new Tech(); tech.setName("老师1"); <br>
	 *            User user1 = new User(); user1.setName("张三"); <br>
	 *            tech.setUsers(Lists.newArrayList(user1)); */
	public static String toJson(Object value, ExceptProperties... exceptProperties) {
		ObjectMapper objectMapper = new ObjectMapper();

		if (exceptProperties != null && exceptProperties.length > 0) {
			SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
			objectMapper.setFilterProvider(simpleFilterProvider);
			for (int i = 0; i < exceptProperties.length; i++) {
				ExceptProperties ep = exceptProperties[i];
				simpleFilterProvider.addFilter(getJsonFilterId(filterClasses.get(i)), SimpleBeanPropertyFilter.filterOutAllExcept(ep.properties.toArray(new String[0])));
				objectMapper.addMixIn(ep.clazz, filterClasses.get(i));
			}
		}

		String jsonStr = null;
		try {
			jsonStr = objectMapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return jsonStr;
	}

	public static class ExceptProperties {

		private Class<?> clazz;

		private List<String> properties;

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

		public List<String> getProperties() {
			return properties;
		}

		public void setProperties(List<String> properties) {
			this.properties = properties;
		}

		public static ExceptProperties create(Class<?> clazz, String... properties) {
			ExceptProperties exceptProperties = new ExceptProperties();
			exceptProperties.setClazz(clazz);
			exceptProperties.setProperties(Lists.newArrayList(properties));
			return exceptProperties;
		}
	}

	protected static final List<Class<?>> filterClasses = new ArrayList<Class<?>>();

	static {
		filterClasses.add(FilterMixIn1.class);
		filterClasses.add(FilterMixIn2.class);
		filterClasses.add(FilterMixIn3.class);
		filterClasses.add(FilterMixIn4.class);
		filterClasses.add(FilterMixIn5.class);
		filterClasses.add(FilterMixIn6.class);
		filterClasses.add(FilterMixIn7.class);
		filterClasses.add(FilterMixIn8.class);
	}

	@JsonFilter("FilterMixIn1")
	private static interface FilterMixIn1 {}

	@JsonFilter("FilterMixIn2")
	private static interface FilterMixIn2 {}

	@JsonFilter("FilterMixIn3")
	private static interface FilterMixIn3 {}

	@JsonFilter("FilterMixIn4")
	private static interface FilterMixIn4 {}

	@JsonFilter("FilterMixIn5")
	private static interface FilterMixIn5 {}

	@JsonFilter("FilterMixIn6")
	private static interface FilterMixIn6 {}

	@JsonFilter("FilterMixIn7")
	private static interface FilterMixIn7 {}

	@JsonFilter("FilterMixIn8")
	private static interface FilterMixIn8 {}

	/** 获取要转换成json的对象上面配置的JsonFilter
	 * 
	 * @param clazz
	 * @return */
	private static String getJsonFilterId(Class<?> clazz) {
		Annotation annotation = AnnotationUtils.findAnnotation(clazz, JsonFilter.class);
		if (null == annotation) {
			throw new NullPointerException("请为:" + clazz.getCanonicalName() + ":配置JsonFilter注解");
		}
		String jsonFilterId = AnnotationUtils.getValue(annotation).toString();
		if (StringHelper.isBlank(jsonFilterId)) {
			throw new NullPointerException("类：" + clazz.getCanonicalName() + "上的JsonFilter注解value必须指定。");
		}

		return jsonFilterId;
	}

	public static Map<?, ?> string2map(String jsonStr) throws com.fasterxml.jackson.core.JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> map = mapper.readValue(jsonStr, Map.class);
		return map;
	}

	/** 使用泛型方法，把json字符串转换为相应的JavaBean对象。 (1)转换为普通JavaBean：readValue(json,Student.class) (2)转换为List,如List<Student>,将第二个参数传递为Student [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 *
	 * @param jsonStr
	 * @param valueType
	 * @return */
	public static <T> T readValue(String jsonStr, Class<T> valueType) throws IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}

		return objectMapper.readValue(jsonStr, valueType);
	}

}
