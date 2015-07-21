package cn.net.guu.system.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.system.model.SysAuthority;

public interface SysAuthorityMapper extends BaseMapper {
	
	/**
	 * 通过用户登录名获得 用户的权限集合
	* <p>Title: selectAuthoritiesByLoginName</p>
	* <p>Description: </p>
	* @param loginName 用户登录名
	* @return
	 */
	public List<SysAuthority> selectAuthoritiesByLoginName(@Param(value="loginName")String loginName);
}