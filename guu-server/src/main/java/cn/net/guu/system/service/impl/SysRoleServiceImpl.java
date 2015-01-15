package cn.net.guu.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.SysRoleMapper;
import cn.net.guu.system.model.SysRole;
import cn.net.guu.system.service.SysRoleService;

/**
 * 角色接口实现类
 * 
* <p>Title: SysRoleServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */

@Service
public class SysRoleServiceImpl extends BaseServiceImpl implements
		SysRoleService {
	
	/**
	 * 角色mapper
	 */
	private SysRoleMapper sysRoleMapper;		
	
	public SysRoleMapper getSysRoleMapper() {
		return sysRoleMapper;
	}

	@Resource
	public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
		super.mapper = sysRoleMapper;
		super.mapperPath = SysRoleMapper.class.getName();
		this.sysRoleMapper = sysRoleMapper;
	}

	@Override
	public List<SysRole> selectRolesByLoginName(String loginName)
			throws SQLException {
		// TODO Auto-generated method stub		
		return sysRoleMapper.selectRolesByLoginName(loginName);
	}

}
