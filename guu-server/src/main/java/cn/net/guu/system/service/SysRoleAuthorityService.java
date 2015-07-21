package cn.net.guu.system.service;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysRoleAuthority;

/**
 * 角色-权限 接口
 * 
* <p>Title: SysRoleAuthorityService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年7月21日
 */
public interface SysRoleAuthorityService extends BaseService
{

	/**
	 * 批量添加角色-权限对应关系
	* <p>Title: addBatch</p>
	* @param roleAuthorities
	* @return
	* @throws SQLException
	 */
	public Integer addBatch(List<SysRoleAuthority> roleAuthorities) throws SQLException;
	
	/**
	 * 删除角色ids对应的权限关系
	* <p>Title: delByRoleIds</p>
	* @param roleIds 角色id集合
	* @return
	* @throws SQLException
	 */
	public Integer delByRoleIds(List<String> roleIds) throws SQLException;
	
	/**
	 * 通过roleId删除  角色与权限对应关系
	* <p>Title: delByRoleId</p>
	* @param roleId
	* @return
	* @throws SQLException
	 */
	public Integer delByRoleId (String roleId) throws SQLException;
	
	/**
	 * 通过角色id获得 角色-权限的对应关系集合
	* <p>Title: queryRoleAuthorityByRoleId</p>
	* @param roleId
	* @return
	* @throws SQLException
	 */
	public List<SysRoleAuthority> queryRoleAuthorityByRoleId(String roleId) throws SQLException;
}
