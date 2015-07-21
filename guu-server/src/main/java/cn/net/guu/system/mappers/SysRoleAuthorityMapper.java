package cn.net.guu.system.mappers;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.system.model.SysRoleAuthority;

public interface SysRoleAuthorityMapper extends BaseMapper{
	
	/**
	 * 批量添加 角色-权限 对应关系
	* <p>Title: insertBatch</p>
	* @param roleAuthority
	* @return
	* @throws SQLException
	 */
	public Integer insertBatch(List<SysRoleAuthority> roleAuthority) throws SQLException; 

}