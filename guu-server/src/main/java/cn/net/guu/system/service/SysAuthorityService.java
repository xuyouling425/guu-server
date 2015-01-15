package cn.net.guu.system.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysAuthority;

/**
 * 权限接口
* <p>Title: SysAuthorityServece</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年8月2日
 */
public interface SysAuthorityService extends BaseService {

	/**
	 * 通过用户登录名，获得该用户对应的权限信息
	* <p>Title: selectAuthoritiesByLoginName</p>
	* <p>Description: </p>
	* @param loginName
	* @return
	* @throws SQLException
	 */
	public Collection<GrantedAuthority> loadUserAuthoritiesByName(String loginName) throws SQLException;
}
