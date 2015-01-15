package cn.net.guu.system.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.system.model.SysRole;

public interface SysRoleMapper extends BaseMapper {
	
	/**
	 * 通过用户登录名，获得当前用户的角色集合信息
	* <p>Title: selectRolesByLoginName</p>
	* <p>Description: </p>
	* @param loginName
	* @return
	 */
 public List<SysRole> selectRolesByLoginName(@Param(value="loginName")String loginName);
}