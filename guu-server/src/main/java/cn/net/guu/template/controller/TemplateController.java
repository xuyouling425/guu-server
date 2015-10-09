package cn.net.guu.template.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.common.SystemPath;
import cn.net.guu.core.utils.UploadUtils;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.template.model.Template;
import cn.net.guu.template.service.TemplateService;

/**
 * 模板controller类
 * <p>
 * Title: TemplateController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年9月7日
 */

@Controller
@RequestMapping("/template")
public class TemplateController
{
	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(TemplateController.class);

	@Resource(name = "templateServiceImpl")
	private TemplateService templateService;

	private final String TEMPLATE_PATH = "admin/tempmanage";

	/**
	 * 获得开发者的模板信息
	 * <p>
	 * Title: queryMyTemplate
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/myTemplate")
	public ModelAndView queryMyTemplate()
	{
		ModelAndView mav = new ModelAndView(TEMPLATE_PATH + "/templateList");
		// 获得当前登录用户
//		SysUser user = new SysUser();
		// UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
		// .getAuthentication()
		// .getPrincipal();
		try
		{
			List<Template> templates = templateService.queryByUserId("guu00001");
			mav.addObject("templateList", templates);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error("Query my template failed.", e);
		}

		return mav;
	}

	/**
	 * 跳转到新增模板页面
	 * <p>
	 * Title: toAddTemplate
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAddTemplate()
	{
		ModelAndView mav = new ModelAndView(TEMPLATE_PATH + "/addTemplate");
		return mav;
	}

	/**
	 * 添加一个模板
	 * <p>
	 * Title: addTemplate
	 * </p>
	 * 
	 * @param request
	 * @param template
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addTemplate(HttpServletRequest request, Template template)
	{

		// 通过模板id，创建模板目录
		creatTemplateDir(template.getTemplateId());

		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		template.setTemplateImg(imgPath);
		template.setStatus(CommonKey.TEMPLATE_APPROVAL_CREATE);
		template.setCreateTime(new Date());
		template.setUserId("guu00001");
		template.setUserName("admin");

		try
		{
			templateService.add(template);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add template failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return queryMyTemplate();
	}

	/**
	 * 跳转到模板的文件列表页面，可以对模板的文件，进行上传，修改，下载
	 * <p>
	 * Title: toTemplateFile
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toTemplateFile")
	public ModelAndView toTemplateFile(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView(TEMPLATE_PATH + "/templateFileList");
		String templateId = request.getParameter("pid");
		try
		{
			Template template = (Template) templateService.selectBypk(templateId);
			if (null == template)
			{
				return queryMyTemplate();
			}

			mav.addObject("template", template);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return mav;
	}

	/**
	 * 在WEB-INF/templates下创建目录
	 * <p>
	 * Title: creatTemplateDir
	 * </p>
	 * 
	 * @param dirName
	 * @return
	 */
	private boolean creatTemplateDir(String dirName)
	{
		dirName = SystemPath.getWebroot() + CommonKey.TEMPLATES_PATH + dirName;
		return UploadUtils.createDir(dirName);
	}

	/**
	 * 删除模板
	 * <p>
	 * Title: deleteTemplate
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView deleteTemplate(HttpServletRequest request)
	{
		String templateId = request.getParameter("pid");

		try
		{
			// 删除数据库记录
			templateService.deleteBypk(templateId);
			
			// 删除模板文件，文件夹
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error("delete template failed.",e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return queryMyTemplate();
	}
	
	/**
	 * 跳转到更新模板信息
	* <p>Title: toUpdate</p>
	* @param request
	* @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView(TEMPLATE_PATH+"updateTemplate");
		String templateId = request.getParameter("pid");
		try
		{
			Template template = (Template) templateService.selectBypk(templateId);
			mav.addObject("template", template);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.error("query template failed.",e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return mav;
	}
	
	/**
	 * 更新模板
	* <p>Title: update</p>
	* @param request
	* @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request,Template template)
	{
		try
		{
			templateService.updateBypkSelective(template);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.error("update template failed.",e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return queryMyTemplate();
		
	}
}
