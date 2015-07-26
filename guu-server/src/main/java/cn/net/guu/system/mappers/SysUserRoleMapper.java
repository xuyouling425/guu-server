package cn.net.guu.system.mappers;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.system.model.SysUserRole;


public interface SysUserRoleMapper extends BaseMapper{
  
	/**
	 * 批量添加用户角色信息
	* <p>Title: addUserRoleBatch</p>
	* @param userRoles
	* @return
	* @throws SQLException
	 */
	public Integer insertBatch(List<SysUserRole> userRoles) throws SQLException;
}