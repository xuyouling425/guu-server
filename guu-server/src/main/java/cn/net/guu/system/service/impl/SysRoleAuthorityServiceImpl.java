package cn.net.guu.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.SysRoleAuthorityMapper;
import cn.net.guu.system.model.SysRoleAuthority;
import cn.net.guu.system.model.SysRoleAuthorityExample;
import cn.net.guu.system.model.SysRoleAuthorityExample.Criteria;
import cn.net.guu.system.service.SysRoleAuthorityService;

/**
 * 角色-权限   接口实现类
* <p>Title: SysRoleAuthorityServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年7月21日
 */

@Service
public class SysRoleAuthorityServiceImpl extends BaseServiceImpl implements SysRoleAuthorityService
{
	/**
	 * SysRoleAuthorityMapper
	 */
	private SysRoleAuthorityMapper sysRoleAuthorityMapper;
	
	public SysRoleAuthorityMapper getSysRoleAuthorityMapper()
	{
		return sysRoleAuthorityMapper;
	}
	
	@Resource
	public void setSysRoleAuthorityMapper(SysRoleAuthorityMapper sysRoleAuthorityMapper)
	{
		super.mapper = sysRoleAuthorityMapper;
		super.mapperPath = SysRoleAuthorityMapper.class.getName();
		this.sysRoleAuthorityMapper = sysRoleAuthorityMapper;
	}

	@Override
	public Integer addBatch(List<SysRoleAuthority> roleAuthorities) throws SQLException
	{
		// TODO Auto-generated method stub
		return sysRoleAuthorityMapper.insertBatch(roleAuthorities);
	}

	@Override
	public Integer delByRoleIds(List<String> roleIds) throws SQLException
	{
		// TODO Auto-generated method stub
		SysRoleAuthorityExample example = new SysRoleAuthorityExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdIn(roleIds);
		return sysRoleAuthorityMapper.deleteByExample(example);
	}

	@Override
	public List<SysRoleAuthority> queryRoleAuthorityByRoleId(String roleId) throws SQLException
	{
		// TODO Auto-generated method stub
		SysRoleAuthorityExample example = new SysRoleAuthorityExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return (List<SysRoleAuthority>) sysRoleAuthorityMapper.selectByExample(example);
	}

	@Override
	public Integer delByRoleId(String roleId) throws SQLException
	{
		// TODO Auto-generated method stub
		SysRoleAuthorityExample example = new SysRoleAuthorityExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return sysRoleAuthorityMapper.deleteByExample(example);
	}

}
