package cn.net.guu.template.service;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.template.model.Template;

/**
 * 模板接口
* <p>Title: TemplateService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年9月7日
 */
public interface TemplateService extends BaseService
{

	/**
	 * 通过用户id，查询出用户当前的所有模板信息
	* <p>Title: queryByUserId</p>
	* @param userId 用户id
	* @return
	* @throws SQLException
	 */
	public List<Template> queryByUserId(String userId) throws SQLException;
}
