package cn.net.guu.system.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.SysAuthorityResourcesMapper;
import cn.net.guu.system.model.SysAuthorityResources;
import cn.net.guu.system.model.SysAuthorityResourcesExample;
import cn.net.guu.system.model.SysAuthorityResourcesExample.Criteria;
import cn.net.guu.system.service.SysAuthorityResourcesService;

/**
 * 权限-资源 接口
 * <p>
 * Title: SysAuthorityResourcesServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年7月15日
 */
@Service
public class SysAuthorityResourcesServiceImpl extends BaseServiceImpl implements SysAuthorityResourcesService
{

	private SysAuthorityResourcesMapper sysAuthorityResourcesMapper;

	@Resource
	public void setSysAuthorityResourcesMapper(SysAuthorityResourcesMapper sysAuthorityResourcesMapper)
	{
		super.mapper = sysAuthorityResourcesMapper;
		super.mapperPath = SysAuthorityResourcesMapper.class.getName();
		this.sysAuthorityResourcesMapper = sysAuthorityResourcesMapper;

	}

	public SysAuthorityResourcesMapper getSysAuthorityResourcesMapper()
	{
		return sysAuthorityResourcesMapper;
	}

	public int addBatch(List<SysAuthorityResources> authorityResources) throws SQLException
	{
		return sysAuthorityResourcesMapper.insertBatch(authorityResources);
	}

	@Override
	public int delBatchByAuthorityIds(List<String> authorityIds) throws SQLException
	{
		// TODO Auto-generated method stub
		SysAuthorityResourcesExample example = new SysAuthorityResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andAuthorityIdIn(authorityIds);
		return sysAuthorityResourcesMapper.deleteByExample(example);
	}

	@Override
	public List<SysAuthorityResources> selectAuResourcesByAuId(String auId) throws SQLException
	{
		// TODO Auto-generated method stub
		List<SysAuthorityResources> auResourcesList = new ArrayList<SysAuthorityResources>();
		SysAuthorityResourcesExample example = new SysAuthorityResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andAuthorityIdEqualTo(auId);
		auResourcesList = (List<SysAuthorityResources>) sysAuthorityResourcesMapper.selectByExample(example);
		return auResourcesList;
	}

	@Override
	public int delByAuthorityId(String authorityId) throws SQLException
	{
		// TODO Auto-generated method stub
		SysAuthorityResourcesExample example = new SysAuthorityResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andAuthorityIdEqualTo(authorityId);
		return sysAuthorityResourcesMapper.deleteByExample(example);
	}

}
