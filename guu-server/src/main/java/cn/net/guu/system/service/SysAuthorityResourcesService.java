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
	
	/**
	 * 通过权限id集合，删除权限和资源的对应关系
	* <p>Title: delBatchByAuthorityIds</p>
	* @param authorityIds 权限id集合
	* @return
	* @throws SQLException
	 */
	public int delBatchByAuthorityIds(List<String> authorityIds) throws SQLException;
	
	/**
	 * 通过权限id，删除权限和资源的对应关系
	* <p>Title: delBatchByAuthorityIds</p>
	* @param authorityId 权限id
	* @return
	* @throws SQLException
	 */
	public int delByAuthorityId(String authorityId) throws SQLException;
	
	/**
	 * 查询一个权限对应的资源集合
	* <p>Title: queryAuResourcesByAuId</p>
	* @param auId 权限id
	* @return
	* @throws SQLException
	 */
	public List<SysAuthorityResources> selectAuResourcesByAuId(String auId) throws SQLException;
}
