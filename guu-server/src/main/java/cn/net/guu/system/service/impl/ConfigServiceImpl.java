package cn.net.guu.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.ConfigMapper;

/**
 * 配置项接口实现类
 * 
 * <p>
 * Title: ConfigServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年11月7日
 */

@Service
public class ConfigServiceImpl extends BaseServiceImpl implements BaseService
{

	private ConfigMapper configMapper;

	public ConfigMapper getConfigMapper()
	{
		return configMapper;
	}

	@Resource
	public void setConfigMapper(ConfigMapper configMapper)
	{
		super.mapper = configMapper;
		super.mapperPath = ConfigMapper.class.getName();
		this.configMapper = configMapper;
	}

}
