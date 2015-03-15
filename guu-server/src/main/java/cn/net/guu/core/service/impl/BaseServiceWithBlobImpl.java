package cn.net.guu.core.service.impl;

import java.util.List;

import cn.net.guu.core.mappers.BaseExample;
import cn.net.guu.core.mappers.BaseMapperWithBlob;
import cn.net.guu.core.service.BaseServiceWithBlob;

/**
 * 对包含大字段的接口实现
* <p>Title: BaseServiceWithBlobImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年3月15日
 */
public class BaseServiceWithBlobImpl extends BaseServiceImpl implements BaseServiceWithBlob
{


	/**
	 * mapper接口
	 */
	protected BaseMapperWithBlob mapperWithBlob;

	
	@Override
	public List<?> selectByExampleWithBLOBs(BaseExample example)
	{
		// TODO Auto-generated method stub
		return mapperWithBlob.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updateByExampleWithBLOBs(Object record, BaseExample example)
	{
		// TODO Auto-generated method stub
		return mapperWithBlob.updateByExampleWithBLOBs(record, example);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Object record)
	{
		// TODO Auto-generated method stub
		return mapperWithBlob.updateByPrimaryKeyWithBLOBs(record);
	}

}
