package cn.net.guu.cms.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.cms.model.Project;
import cn.net.guu.cms.service.ProjectService;
import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.UploadUtils;

/**
 * 项目/案例接口类
 * <p>
 * Title: BusinessController
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

@Controller
@RequestMapping("/project")
public class ProjectController
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(ProjectController.class);

	@Resource(name = "projectServiceImpl")
	private ProjectService projectService;

	/**
	 * 查询出所有的项目/案例（业务）信息
	 * <p>
	 * Title: queryProject
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	public ModelAndView queryProject()
	{
		log.info("Entering queryProject()..");
		ModelAndView mav = new ModelAndView("admin/project");
		try
		{
			List<Project> projects = (List<Project>) projectService.selectByExample(null);
			mav.addObject("projects", projects);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Select queryProject Faild.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		log.info("Exiting queryProject()..");
		return mav;
	}

	/**
	 * 新增一个项目/案例
	 * <p>
	 * Title: addProject
	 * </p>
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addProject(HttpServletRequest request, Project project)
	{
		log.info("Entering addProject()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		// 设置上传图片
		project.setImage(imgPath);
		// 设置主键
		project.setPid(CommonUtils.getPrimaryKey());

		try
		{
			// 调用添加接口
			projectService.add(project);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add project Failed.", e);
		}

		log.info("Exiting addProject()..");
		return queryProject();
	}

	/**
	 * 跳转到添加项目/案例页面
	 * <p>
	 * Title: toAddProject
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAddProject()
	{
		return new ModelAndView("admin/addProject");
	}

	/**
	 * 跳转到修改项目/案例页面
	 * <p>
	 * Title: toUpdate
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request)
	{
		String pid = request.getParameter("pid");
		ModelAndView mav = new ModelAndView("admin/updateProject");
		Project project = new Project();
		if (!StringUtils.isEmpty(pid))
		{
			try
			{
				project = (Project) projectService.selectBypk(pid);

				mav.addObject("project", project);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Select Business Failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		} else
		{
			// pid传值为空，直接返回查看页面
			return queryProject();
		}

		return mav;
	}

	/**
	 * 新增一个项目/案例
	 * <p>
	 * Title: updateProject
	 * </p>
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView updateProject(HttpServletRequest request, Project project)
	{
		log.info("Entering updateProject()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		// 设置上传图片
		if (!StringUtils.isEmpty(imgPath))
		{
			project.setImage(imgPath);
		}
		try
		{
			// 更新接口
			projectService.updateByPrimaryKeyWithBLOBs(project);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Update project Failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		log.info("Exiting updateProject()..");
		return queryProject();
	}

	/**
	 * 删除一个 项目/案例信息
	 * <p>
	 * Title: delete
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request)
	{
		String pid = request.getParameter("pid");
		if (!StringUtils.isEmpty(pid))
		{
			try
			{
				projectService.deleteBypk(pid);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Delete Business Failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		}
		return queryProject();
	}
}
