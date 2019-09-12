package com.pronghorn.core.generic;

import java.util.List;

/** 所有自定义Service的顶级接口,封装常用的增删查改操作
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型
 * @version 2017年5月12日 15:17:31 王磊 增加 find 方法 */
public interface CommonService<Model, PK> {

	/** 通过主键, 查询对象
	 *
	 * @param id 主键
	 * @return */
	Model getOne(PK id);

	/** 通过主键, 删除对象
	 *
	 * @param id 主键 */
	int deleteById(PK id);

	/** 插入对象
	 *
	 * @param model 对象 */
	int save(Model model);

	/** 插入对象并Flush
	 *
	 * @param model 对象 */
	Model saveAndFlush(Model model);


	List<Model> findAll();

}
