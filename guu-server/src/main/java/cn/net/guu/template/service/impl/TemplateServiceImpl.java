package cn.net.guu.template.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.template.mappers.TemplateMapper;
import cn.net.guu.template.model.Template;
import cn.net.guu.template.model.TemplateExample;
import cn.net.guu.template.model.TemplateExample.Criteria;
import cn.net.guu.template.service.TemplateService;

/**
 * 模板接口实现类
* <p>Title: TemplateServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年9月7日
 */

@Service
public class TemplateServiceImpl extends BaseServiceImpl implements TemplateService
{

	private TemplateMapper templateMapper;

	public TemplateMapper getTemplateMapper()
	{
		return templateMapper;
	}

	@Resource
	public void setTemplateMapper(TemplateMapper templateMapper)
	{
		this.templateMapper = templateMapper;
		super.mapper = templateMapper;
		super.mapperPath = TemplateMapper.class.getName();
	}

	@Override
	public List<Template> queryByUserId(String userId) throws SQLException
	{
		// TODO Auto-generated method stub
		TemplateExample example = new TemplateExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return (List<Template>) templateMapper.selectByExample(example);
	}

	@Override
	public List<Template> queryByStatus(Integer status) throws SQLException
	{
		// TODO Auto-generated method stub
		TemplateExample example = new TemplateExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);
		return (List<Template>) templateMapper.selectByExample(example);
	}


	
	
	
}
