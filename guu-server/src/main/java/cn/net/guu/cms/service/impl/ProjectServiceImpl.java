package cn.net.guu.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.cms.mappers.ProjectMapper;
import cn.net.guu.cms.service.ProjectService;
import cn.net.guu.core.service.impl.BaseServiceWithBlobImpl;

/**
 * 项目（案例）实现接口
* <p>Title: ProjectServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年3月15日
 */
@Service
public class ProjectServiceImpl extends BaseServiceWithBlobImpl implements ProjectService
{

	private ProjectMapper projectMapper;

	public ProjectMapper getProjectMapper()
	{
		return projectMapper;
	}

	@Resource
	public void setProjectMapper(ProjectMapper projectMapper)
	{
		super.mapper = projectMapper;
		super.mapperWithBlob = projectMapper;
		super.mapperPath = ProjectMapper.class.getName();
		this.projectMapper = projectMapper;
	}
	
}
