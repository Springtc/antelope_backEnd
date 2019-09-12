package com.pronghorn.core.generic;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;
import com.pronghorn.core.helper.ReflectionHelper;
import com.pronghorn.core.helper.SpringBeanHelper;
import com.pronghorn.core.helper.StringHelper;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.FailedResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/** GenericService的实现类, 其他的自定义 ServiceImpl, 继承自它,可以获得常用的增删查改操作, 未实现的方法有 子类各自实现
 * <p/>
 * Model : 代表数据库中的表 映射的Java对象类型 PK :代表对象的主键类型
 *
 */
public abstract class GenericController<Model, PK> {

	private static Logger logger = LoggerFactory.getLogger(GenericController.class);

	/** 获取 对象 分页信息
	 *
	 * @param pageVo 对象条件
	 * @return 当前页信息 */
	@RequestMapping(value = "/batch/v1", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> findPageV4(@RequestBody(required = false) PageVo<Model> pageVo) {
		try {
			int offset = pageVo.getOffset();
			int limit = pageVo.getLimit();
			PageHelper.startPage(offset, limit);// page为当前页码，rows为每页显示的记录数
			if(StringHelper.isNotBlank(pageVo.getOrderBy())){
				PageHelper.orderBy(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pageVo.getOrderBy()));
			}
			PageInfo<Model> page = (PageInfo<Model>) ReflectionHelper.invokeMethodByName(this.getService(), "findPage", pageVo.getModel());

			return new ResponseEntity<CommonResponse>(SuccessResponse.create(page, "查询成功"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse>(FailedResponse.create(e.getMessage(), "查询失败"), HttpStatus.OK);
		}finally {
			PageHelper.clearPage();
		}
	}

	/** 获取 对象 集合信息
	 *
	 * @param model 对象条件
	 * @return 当前页信息 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> find(@RequestBody(required = false) Model model) {
		try {
			List<Model> list = (List<Model>) ReflectionHelper.invokeMethodByName(this.getService(), "find", model);

			return new ResponseEntity<CommonResponse>(SuccessResponse.create(list, "查询成功"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CommonResponse>(FailedResponse.create(e.getMessage(), "查询失败"), HttpStatus.OK);
		}
	}

	/** 获取所有对象信息接口
	 *
	 * @return 所有对象信息清单JSON */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CommonResponse> getAll() {
		List<Model> models = (List<Model>) ReflectionHelper.invokeMethodByName(this.getService(), "getAll");
		return new ResponseEntity<CommonResponse>(SuccessResponse.create(models, "查询成功"), HttpStatus.OK);
	}

	/** 根据Empuid获取对象信息接口
	 *
	 * @param id 主键
	 * @return 对象信息JSON */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> getModel(@PathVariable("id") PK id) {
		logger.info("GetModel By ID" + id);
		Model model = (Model) ReflectionHelper.invokeMethodByName(this.getService(), "getByPrimaryKey", id);
		if (model == null) {
			return new ResponseEntity<CommonResponse>(FailedResponse.create("没有查询到数据"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CommonResponse>(SuccessResponse.create(model, "查询成功"), HttpStatus.OK);
	}

	/** 创建对象
	 *
	 * @param model 待创建的对象
	 * @return 执行状态 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> createModel(@RequestBody Model model) {
		logger.info("Create Model " + model);

		Object id = ReflectionHelper.invokeGetter(model, "id");

		if (null == id) {
			ReflectionHelper.invokeMethodByName(this.getService(), "insert", model);
			return new ResponseEntity<CommonResponse>(SuccessResponse.create(ReflectionHelper.getFieldValue(model, "id"), "创建成功"), HttpStatus.OK);
		}

		ReflectionHelper.invokeMethodByName(this.getService(), "update", model);

		return new ResponseEntity<CommonResponse>(SuccessResponse.create(null, "更新成功"), HttpStatus.OK);
	}

	/** 根据 主键 更新对象数据
	 *
	 * @param id 主键
	 * @param model 待更新的对象数据
	 * @return */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CommonResponse> updateModel(@PathVariable("id") PK id, @RequestBody Model model) {
		logger.info("Updating Model With PK " + id);

		Model existModel = (Model) ReflectionHelper.invokeMethodByName(this.getService(), "getByPrimaryKey", id);

		if (existModel == null) {
			logger.info("Model With PK " + id + " not found");
			return new ResponseEntity<CommonResponse>(HttpStatus.NOT_FOUND);
		}

		ReflectionHelper.invokeMethodByName(this.getService(), "update", model);
		return new ResponseEntity<CommonResponse>(SuccessResponse.create(null, "更新成功"), HttpStatus.OK);
	}

	/** 根据 主键 删除对象信息
	 *
	 * @param id 主键
	 * @return */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponse> deleteModel(@PathVariable("id") PK id) {
		logger.info("Fetching & Deleting Model with PK " + id);
		ReflectionHelper.invokeMethodByName(this.getService(), "delete", id);
		return new ResponseEntity<CommonResponse>(SuccessResponse.create(null, "删除成功"), HttpStatus.OK);
	}

	protected Object getService() {
		Class<Model> persistentClass = ReflectionHelper.getSuperClassGenricType(getClass());

		String classNameOfT = persistentClass.getName();
		String packageType = "";

		String parentPackagePath = classNameOfT.substring(0, StringHelper.lastIndexOfWithTimes(classNameOfT, ".", 3));
		classNameOfT = classNameOfT.replaceAll(parentPackagePath + ".", "");
		packageType = classNameOfT.substring(0, classNameOfT.indexOf("."));

		Object impl = null;
		try {
			impl = SpringBeanHelper.getBeans(Class.forName(parentPackagePath + "." + packageType + ".service.impl." + persistentClass.getSimpleName() + "ServiceImpl"));
		} catch (Exception e) {
			logger.error("context", e);
		}

		return impl;
	}

}
