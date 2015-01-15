package cn.net.guu.system.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysRole;

/**
 * 角色接口，实现角色相关的业务接口
 * 继承 BaseService接口
 * 
* <p>Title: SysRoleService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */

public interface SysRoleService extends BaseService {
	
	/**
	 * 通过用户登录名，获得用户的角色集合
	* <p>Title: selectRolesByLoginName</p>
	* <p>Description: </p>
	* @param loginName 用户登录名
	* @return
	* @throws SQLException
	 */
	public List<SysRole> selectRolesByLoginName(String loginName) throws SQLException;

}
