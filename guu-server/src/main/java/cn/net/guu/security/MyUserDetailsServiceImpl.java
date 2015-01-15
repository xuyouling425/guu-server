package cn.net.guu.security;

import java.sql.SQLException;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.net.guu.security.MyUserDetailsService;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.service.SysAuthorityService;
import cn.net.guu.system.service.SysUserService;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	@Resource(name="sysUserServiceImpl")
	private SysUserService userService;	

	@Resource(name="sysAuthorityServiceImpl")
	private SysAuthorityService authorityServece;

	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException,DataAccessException {
		// TODO Auto-generated method stub

		Collection<GrantedAuthority> auths=null;

		SysUser user = null;		
		try {
			auths = authorityServece.loadUserAuthoritiesByName(loginName);
			user = userService.selectUserByLoginName(loginName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (UserDetails) new SysUser(user.getLoginName(), user.getLoginPassword(), true, true, true, true, auths);

	}

}
