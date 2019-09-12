package com.pronghorn.core.generic;

import com.github.pagehelper.PageInfo;

import java.util.List;

/** 所有自定义Service的顶级接口,封装常用的增删查改操作
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型
 * @version 2017年5月12日 15:17:31 王磊 增加 find 方法 */
public interface GenericService<Model, PK> {

	/** 通过主键, 查询对象
	 *
	 * @param id 主键
	 * @return */
	Model getByPrimaryKey(PK id);

	/** 通过主键, 删除对象
	 *
	 * @param id 主键 */
	int delete(PK id);

	/** 插入对象
	 *
	 * @param model 对象 */
	int insert(Model model);


	/** 更新对象，可选字段
	 *
	 * @param model 对象 */
	int update(Model model);

	List<Model> getAll();

	/** 根据对象条件，获取集合对象
	 *
	 * @param model 对象 */
	List<Model> find(Model model);

	PageInfo<Model> findPage(Model model);

}
