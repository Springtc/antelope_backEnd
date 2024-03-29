package com.pronghorn.core.generic;

import java.util.List;

/** 所有自定义Mapper的顶级接口, 封装常用的增删查改操作, 可以通过Mybatis Generator Maven 插件自动生成Mapper, 也可以手动编码,然后继承CommonMapper 即可.
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型 */
public interface GenericMapper<Model, PK> {

	/** 通过主键, 查询对象
	 *
	 * @param id 主键
	 * @return */
	Model selectByPrimaryKey(PK id);

	/** 通过主键, 删除对象
	 *
	 * @param id 主键 */
	int deleteByPrimaryKey(PK id);

	/** 插入对象
	 *
	 * @param model 对象 */
	int insert(Model model);

	/** 插入对象，可选字段
	 *
	 * @param model 对象 */
	int insertSelective(Model model);

	/** 更新对象，可选字段
	 *
	 * @param model 对象 */
	int updateByPrimaryKeySelective(Model model);

	/** 更新对象
	 *
	 * @param model 对象 */
	int updateByPrimaryKey(Model model);

	/** 获取对象集合
	 *
	 * @param model 对象筛选条件 */
	List<Model> find(Model model);

}
