package cn.net.guu.system.service.impl;


import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.SysAuthorityMapper;
import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.service.SysAuthorityService;

/**
 * 权限接口实现类
* <p>Title: SysAuthorityServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年8月2日
 */

@Service
public class SysAuthorityServiceImpl extends BaseServiceImpl implements SysAuthorityService {
	
	/**
	 * 权限mapper
	 */
	 private SysAuthorityMapper sysAuthorityMapper;

	public SysAuthorityMapper getSysAuthorityMapper() {
		return sysAuthorityMapper;
	}

	@Resource
	public void setSysAuthorityMapper(SysAuthorityMapper sysAuthorityMapper) {
		super.mapper = sysAuthorityMapper;
		super.mapperPath = SysAuthorityMapper.class.getName();
		this.sysAuthorityMapper = sysAuthorityMapper;
	}


	 @Override
	public Collection<GrantedAuthority> loadUserAuthoritiesByName(String loginName)
	{
		Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		List<SysAuthority> authoritieList = sysAuthorityMapper.selectAuthoritiesByLoginName(loginName);
		
		for(SysAuthority authority:authoritieList)
		{
			
			SimpleGrantedAuthority authorityImpl = new SimpleGrantedAuthority(authority.getAuthorityCode());
			auths.add(authorityImpl);
		}
		
		return auths;
	}
	 

}
