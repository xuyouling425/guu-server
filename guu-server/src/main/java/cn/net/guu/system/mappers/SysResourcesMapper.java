package cn.net.guu.system.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.system.model.SysResources;

public interface SysResourcesMapper extends BaseMapper {
   
	/**
	 * 通过用户登录名，获得当前用户的资源集合
	* <p>Title: selectRolesByLoginName</p>
	* <p>Description: </p>
	* @param loginName 登录名
	* @return
	 */
 public List<SysResources> selectResourcesByLoginName(@Param(value="loginName")String loginName);
 
 
	/**
	 * 通过用户登录名，获得当前用户的资源集合
	* <p>Title: selectRolesByLoginName</p>
	* <p>Description: </p>
	* @param authCode 权限编码
	* @return
	 */
public List<SysResources> selectResourcesByAuthCode(@Param(value="authCode")String authCode);
 
}