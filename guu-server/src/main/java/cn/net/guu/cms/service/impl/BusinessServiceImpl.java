package cn.net.guu.cms.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.cms.mappers.BusinessMapper;
import cn.net.guu.cms.model.Business;
import cn.net.guu.cms.model.BusinessExample;
import cn.net.guu.cms.service.BusinessService;
import cn.net.guu.core.service.impl.BaseServiceImpl;

/**
 * 服务接口实现类
 * <p>
 * Title: BusinessServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年2月13日
 */
@Service
public class BusinessServiceImpl extends BaseServiceImpl implements BusinessService
{

	private BusinessMapper businessMapper;

	public BusinessMapper getBusinessMapper()
	{
		return businessMapper;
	}

	@Resource
	public void setBusinessMapper(BusinessMapper businessMapper)
	{
		super.mapper = businessMapper;
		super.mapperPath = BusinessMapper.class.getName();
		this.businessMapper = businessMapper;
	}

	@Override
	public int updateByExampleWithBLOBs(Business business,BusinessExample example) throws SQLException
	{
		// TODO Auto-generated method stub
		return businessMapper.updateByExampleWithBLOBs(business,example);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Business business) throws SQLException
	{
		// TODO Auto-generated method stub
		return businessMapper.updateByPrimaryKeyWithBLOBs(business);
	}

	
}
