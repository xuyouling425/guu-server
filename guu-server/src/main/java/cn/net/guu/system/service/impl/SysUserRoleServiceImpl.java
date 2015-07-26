package cn.net.guu.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.SysUserRoleMapper;
import cn.net.guu.system.model.SysUserRole;
import cn.net.guu.system.model.SysUserRoleExample;
import cn.net.guu.system.model.SysUserRoleExample.Criteria;
import cn.net.guu.system.service.SysUserRoleService;

/**
 * 用户角色 接口实现类
 * <p>
 * Title: SysUserRoleServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年7月26日
 */

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl implements SysUserRoleService
{

	private SysUserRoleMapper sysUserRoleMapper;

	public SysUserRoleMapper getSysUserRoleMapper()
	{
		return sysUserRoleMapper;
	}

	@Resource
	public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper)
	{
		super.mapper = sysUserRoleMapper;
		super.mapperPath = SysUserRoleMapper.class.getName();
		this.sysUserRoleMapper = sysUserRoleMapper;
	}

	@Override
	public List<SysUserRole> selectUserRoleByUserId(String userId) throws SQLException
	{
		// TODO Auto-generated method stub
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);

		return (List<SysUserRole>) sysUserRoleMapper.selectByExample(example);
	}

	@Override
	public Integer delUserRoleByUserId(String userId) throws SQLException
	{
		// TODO Auto-generated method stub
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return sysUserRoleMapper.deleteByExample(example);
	}

	@Override
	public Integer delUserRoleByUserIds(List<String> userIdList) throws SQLException
	{
		// TODO Auto-generated method stub
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdIn(userIdList);
		return sysUserRoleMapper.deleteByExample(example);
	}

	@Override
	public Integer addUserRoleBatch(List<SysUserRole> userRoles) throws SQLException
	{
		// TODO Auto-generated method stub
		return sysUserRoleMapper.insertBatch(userRoles);
	}

}
