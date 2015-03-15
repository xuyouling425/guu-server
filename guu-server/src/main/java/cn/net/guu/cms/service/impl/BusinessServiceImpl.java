package cn.net.guu.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.cms.mappers.BusinessMapper;
import cn.net.guu.cms.service.BusinessService;
import cn.net.guu.core.service.impl.BaseServiceWithBlobImpl;

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
public class BusinessServiceImpl extends BaseServiceWithBlobImpl implements BusinessService
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
		super.mapperWithBlob = businessMapper;
		super.mapperPath = BusinessMapper.class.getName();
		this.businessMapper = businessMapper;
	}
	
}
