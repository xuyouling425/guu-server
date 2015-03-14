package cn.net.guu.cms.mappers;

import cn.net.guu.cms.model.Business;
import cn.net.guu.cms.model.BusinessExample;
import cn.net.guu.core.mappers.BaseMapper;

public interface BusinessMapper extends BaseMapper
{
	
	 int updateByPrimaryKeyWithBLOBs(Business business);
	 
	 int updateByExampleWithBLOBs(Business business,BusinessExample example);
}