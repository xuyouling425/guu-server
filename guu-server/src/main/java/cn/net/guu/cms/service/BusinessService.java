package cn.net.guu.cms.service;

import java.sql.SQLException;

import cn.net.guu.cms.model.Business;
import cn.net.guu.cms.model.BusinessExample;
import cn.net.guu.core.service.BaseService;

/**
 * service服务接口，显示公司所提供的服务
* <p>Title: BusinessService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年2月13日
 */
public interface BusinessService extends BaseService
{

	/**
	 * 通过条件更新（包含大字段）
	* <p>Title: updateByExampleWithBLOBs</p>
	* @param business
	* @return
	* @throws SQLException
	 */
	public int updateByExampleWithBLOBs(Business business,BusinessExample example) throws SQLException;
	
	/**
	 * 通过主键更新（包含大字段）
	* <p>Title: updateByPrimaryKeyWithBLOBs</p>
	* @param business
	* @return
	* @throws SQLException
	 */
	public int updateByPrimaryKeyWithBLOBs(Business business) throws SQLException;
}
