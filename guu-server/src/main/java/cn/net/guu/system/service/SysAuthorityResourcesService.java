package cn.net.guu.system.service;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysAuthorityResources;

/**
 * 权限和资源对应关系 接口
* <p>Title: SysAuthorityResourcesService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年7月15日
 */
public interface SysAuthorityResourcesService extends BaseService
{

	/**
	 * 批量添加 权限和资源的对应关系
	* <p>Title: addBatch</p>
	* @param authorityResources
	* @return
	* @throws SQLExceptionl
	 */
	public int addBatch(List<SysAuthorityResources> authorityResources) throws SQLException;
}
