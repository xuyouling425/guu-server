package cn.net.guu.system.service;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysUserRole;

/**
 * 用户-角色 接口
 * 处理批量添加，批量删除用户-角色 对应关系
* <p>Title: SysUserRoleService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年7月26日
 */
public interface SysUserRoleService extends BaseService
{

	/**
	 * 通过用户id 查询用户对应的角色信息
	* <p>Title: queryUserRoleByUserId</p>
	* @param userId 用户id
	* @return 用户对应的角色信息
	* @throws SQLException
	 */
	public List<SysUserRole> selectUserRoleByUserId(String userId) throws SQLException;
	
	/**
	 * 通过用户id，删除用户的角色信息
	* <p>Title: delUserRoleByuserId</p>
	* @param userId 用户id
	* @return
	* @throws SQLException
	 */
	public Integer delUserRoleByUserId(String userId) throws SQLException;
	
	/**
	 * 通过用户id集合，删除对应用户的角色信息
	* <p>Title: delUserRoleByUserIds</p>
	* @param userIdList 用户id集合
	* @return
	* @throws SQLException
	 */
	public Integer delUserRoleByUserIds(List<String> userIdList) throws SQLException;
	
	/**
	 * 批量添加用户-角色的对应关系
	* <p>Title: addUserRoleBatch</p>
	* @param userRoles
	* @return
	* @throws SQLException
	 */
	public Integer addUserRoleBatch(List<SysUserRole> userRoles) throws SQLException;
}
