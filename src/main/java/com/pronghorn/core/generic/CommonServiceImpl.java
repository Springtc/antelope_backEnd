package com.pronghorn.core.generic;

import com.pronghorn.core.helper.ReflectionHelper;
import com.pronghorn.core.helper.SpringBeanHelper;
import com.pronghorn.core.helper.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** GenericService的实现类, 其他的自定义 ServiceImpl, 继承自它,可以获得常用的增删查改操作, 未实现的方法有 子类各自实现
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型
 */
public abstract class CommonServiceImpl<Model, PK> implements CommonService<Model, PK> {

	private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	/** 插入对象
	 *
	 * @param model 对象 */
	public int save(Model model) {
		return (int) ReflectionHelper.invokeMethodByName(this.getMapper(), "save", model);
	}

	/** 插入对象
	 *
	 * @param model 对象 */
	public Model saveAndFlush(Model model) {
		return (Model) ReflectionHelper.invokeMethodByName(this.getMapper(), "saveAndFlush", model);
	}

	/** 通过主键, 删除对象
	 *
	 * @param id 主键 */
	public int deleteById(PK id) {
		return (int) ReflectionHelper.invokeMethodByName(this.getMapper(), "deleteById", id);
	}

	/** 通过主键, 查询对象
	 *
	 * @param id 主键
	 * @return */
	public Model getOne(PK id) {
		return (Model) ReflectionHelper.invokeMethodByName(this.getMapper(), "getOne", id);
	}

	/** 获取所有对象清单
	 *
	 * @return */
	public List<Model> findAll() {
		return (List<Model>) ReflectionHelper.invokeMethod(this.getMapper(), "findAll", new Class<?>[] { Object.class }, new Object[] { null });
	}

	protected Object getMapper() {
		Class<Model> persistentClass = ReflectionHelper.getSuperClassGenricType(getClass());

		String classNameOfT = persistentClass.getName();
		String packageType = "";

		String parentPackagePath = classNameOfT.substring(0, StringHelper.lastIndexOfWithTimes(classNameOfT, ".", 3));
		classNameOfT = classNameOfT.replaceAll(parentPackagePath + ".", "");
		packageType = classNameOfT.substring(0, classNameOfT.indexOf("."));

		Object impl = null;
		try {
			impl = SpringBeanHelper.getBeans(Class.forName(parentPackagePath + "." + packageType + ".repository." + persistentClass.getSimpleName() + "repository"));
		} catch (Exception e) {
			logger.error("context", e);
		}

		return impl;
	}

}
