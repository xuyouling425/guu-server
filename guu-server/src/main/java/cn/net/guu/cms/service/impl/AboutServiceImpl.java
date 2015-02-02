package cn.net.guu.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.cms.mappers.AboutMapper;
import cn.net.guu.cms.service.AboutService;
import cn.net.guu.core.service.impl.BaseServiceImpl;

/**
 * about接口实现类
 * <p>
 * Title: SysAboutServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年1月31日
 */

@Service
public class AboutServiceImpl extends BaseServiceImpl implements AboutService
{

	/**
	 * aboutmapper 类
	 */
	private AboutMapper aboutMapper;

	public AboutMapper getAboutMapper()
	{
		return aboutMapper;
	}
	
	@Resource
	public void setAboutMapper(AboutMapper aboutMapper)
	{
		super.mapper = aboutMapper;
		super.mapperPath = AboutMapper.class.getName();
		this.aboutMapper = aboutMapper;
	}

}
