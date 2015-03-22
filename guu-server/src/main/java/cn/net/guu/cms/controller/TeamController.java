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

import cn.net.guu.cms.cache.WebCache;
import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.UploadUtils;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.service.SysUserService;

/**
 * 团队成员控制器
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
@RequestMapping("/team")
public class TeamController
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(TeamController.class);

	@Resource(name = "sysUserServiceImpl")
	private SysUserService teamService;

	/**
	 * 查询出所有的团队成员信息
	 * <p>
	 * Title: queryProject
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	public ModelAndView query()
	{
		log.info("Entering query()..");
		ModelAndView mav = new ModelAndView("admin/team");
		try
		{
			List<SysUser> teams = (List<SysUser>) teamService.selectUserByType(CommonKey.USER_TYPE_TEAM);
			mav.addObject("teams", teams);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Select query faild.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		log.info("Exiting query()..");
		return mav;
	}

	/**
	 * 新增一个团队成员
	 * <p>
	 * Title: addProject
	 * </p>
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, SysUser team)
	{
		log.info("Entering add()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		// 设置上传图片
		team.setPhoto(imgPath);
		// 设置主键
		team.setUserId(CommonUtils.getPrimaryKey(CommonKey.GUU));
		// 设置用户类型为团队成员类型：team
		team.setUserType(CommonKey.USER_TYPE_TEAM);
		// 设置团队成员成员状态为可用
		team.setUserStatus(CommonKey.ENABLED_INT);

		try
		{
			// 调用添加接口
			teamService.add(team);
			//刷新缓存
			WebCache.getInstance().refreshTeam();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		log.info("Exiting add()..");
		return query();
	}

	/**
	 * 跳转到添加团队成员页面
	 * <p>
	 * Title: toAddProject
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd()
	{
		return new ModelAndView("admin/addTeam");
	}

	/**
	 * 跳转到修改团队成员页面
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
		ModelAndView mav = new ModelAndView("admin/updateTeam");
		SysUser team = new SysUser();
		if (!StringUtils.isEmpty(pid))
		{
			try
			{
				team = (SysUser) teamService.selectBypk(pid);

				mav.addObject("team", team);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Select failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		} else
		{
			// pid传值为空，直接返回查看页面
			return query();
		}

		return mav;
	}

	/**
	 * 更新一个团队成员
	 * <p>
	 * Title: updateProject
	 * </p>
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, SysUser team)
	{
		log.info("Entering update()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		if (!StringUtils.isEmpty(imgPath))
		{
			// 设置上传图片
			team.setPhoto(imgPath);
		}
		try
		{
			// 更新接口
			teamService.updateBypkSelective(team);
			//刷新缓存
			WebCache.getInstance().refreshTeam();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Update ailed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		log.info("Exiting update()..");
		return query();
	}

	/**
	 * 删除一个团队成员信息
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
		log.info("Entering delete()..");
		String pid = request.getParameter("pid");
		if (!StringUtils.isEmpty(pid))
		{
			try
			{
				teamService.deleteBypk(pid);
				//刷新缓存
				WebCache.getInstance().refreshTeam();
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Delete  Failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		}
		log.info("Existing delete()..");
		return query();
	}
}
