package cn.net.guu.system.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.EncryptUtils;
import cn.net.guu.core.utils.UploadUtils;
import cn.net.guu.system.model.SysRole;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.model.SysUserRole;
import cn.net.guu.system.service.SysRoleService;
import cn.net.guu.system.service.SysUserRoleService;
import cn.net.guu.system.service.SysUserService;

/**
 * 用户controller ,处理用户的相关业务逻辑信息
 * <p>
 * Title: SysUserController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2014年7月24日
 */

@Controller
@RequestMapping("/user")
public class SysUserController
{

	/**
	 * 用户接口
	 */
	@Resource(name = "sysUserServiceImpl")
	private SysUserService userService;

	/**
	 * 角色接口
	 */
	@Resource(name = "sysRoleServiceImpl")
	private SysRoleService roleService;

	/**
	 * 用户角色接口
	 */
	@Resource(name = "sysUserRoleServiceImpl")
	private SysUserRoleService userRoleService;

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(SysUserController.class);

	private final String ADMIN_PATH = "admin/sys/user/";

	/**
	 * 跳转到用户登录页 只允许post提交的登录请求
	 * <p>
	 * Title: login
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login()
	{
		return new ModelAndView("admin/login");
	}

	/**
	 * 后台用户登录 只允许post提交的登录请求
	 * <p>
	 * Title: login
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginin")
	public ModelAndView loginin(SysUser loginUser, HttpServletRequest request)
	{
		// 默认后台登录成功跳转页面 管理员首页 index
		String returnName = "admin/index";

		SysUser currentUser = (SysUser) request.getSession().getAttribute(CommonKey.SESSION_LOGIN_NAME);
		// 判定当前用户是否已经在session，如果不存在，则查询用户信息，匹配密码
		if (null == currentUser)
		{
			try
			{
				SysUser user = userService.userLogin(loginUser.getLoginName(), loginUser.getLoginPassword());
				if (user == null)
				{
					// 登录失败，到登录页面
					returnName = "admin/login";
				} else
				{
					request.getSession().setAttribute(CommonKey.SESSION_LOGIN_NAME, user);
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ModelAndView(returnName);
	}

	/**
	 * 后台用户退出
	 * <p>
	 * Title: logout
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request)
	{
		return new ModelAndView("admin/login");
	}

	/**
	 * 查询所有的用户
	 * <p>
	 * Title: queryAll
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public ModelAndView queryAll(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView(ADMIN_PATH + "user");

		try
		{
			log.info("Query all admins.");
			List<SysUser> adminList = userService.selectUserByType(CommonKey.USER_TYPE_ADMIN);
			mav.addObject("adminList", adminList);
		} catch (SQLException e)
		{
			log.error("Query user failed.", e);
			// TODO Auto-generated catch block
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return mav;
	}

	/**
	 * 跳转到新增管理员页面
	 * <p>
	 * Title: toAdd
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView(ADMIN_PATH + "addUser");
		try
		{
			log.info("Query all roles.");
			List<SysRole> roleList = (List<SysRole>) roleService.selectByExample(null);
			mav.addObject("roleList", roleList);
			log.info("Query user roles.");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Query user roles failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return mav;
	}

	/**
	 * 添加一个用户
	 * <p>
	 * Title: addUser
	 * </p>
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addUser(HttpServletRequest request, SysUser user)
	{
		// ModelAndView mav = new ModelAndView(ADMIN_PATH+"add");
		// ModelAndView mav = new ModelAndView("admin/about");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_ADMIN_FILE_PATH);
		user.setPhoto(imgPath);
		user.setCreatTime(new Date());
		//设置用户可用
		user.setUserStatus(CommonKey.ENABLED_INT);
		//管理员类型
		user.setUserType(CommonKey.USER_TYPE_ADMIN);
		String userId = CommonUtils.getPrimaryKey(CommonKey.GUU);
		user.setUserId(userId);
		String newPwd = EncryptUtils.encryptSalt(CommonKey.USER_DEFAULT_PASSWORD, userId);
		user.setLoginPassword(newPwd);
		
		try
		{
			log.info("Add a user." + user);
			userService.add(user);
			// 获得用户的角色信息
			String[] roleIds = request.getParameterValues("role");
			log.info("The select role list is " + roleIds);

			addUserRoleBacth(user, roleIds);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryAll(request);
	}

	/**
	 * 跳转到更新用户信息页面
	 * <p>
	 * Title: toUpdate
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView(ADMIN_PATH + "updateUser");

		String userId = request.getParameter("pids");

		try
		{
			log.info("Query user by userid " + userId);
			SysUser user = (SysUser) userService.selectBypk(userId);
			mav.addObject("user", user);
			log.debug("Query user  roles.");
			List<SysUserRole> userRoleList = userRoleService.selectUserRoleByUserId(userId);
			mav.addObject("userRoleList", userRoleList);
			log.info("Query all roles .");
			List<SysRole> roleList = (List<SysRole>) roleService.selectByExample(null);
			mav.addObject("roleList", roleList);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("To update user failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return mav;
	}

	/**
	 * 显示用户详情
	 * <p>
	 * Title: toUpdate
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/view")
	public ModelAndView viewUser(HttpServletRequest request)
	{
		ModelAndView view = new ModelAndView(ADMIN_PATH + "viewUser");
		// 获得用户要查看的信息
		toUpdate(request);
		return view;
	}

	/**
	 * 更新用户信息
	 * <p>
	 * Title: updateUser
	 * </p>
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView updateUser(HttpServletRequest request, SysUser user)
	{
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_ADMIN_FILE_PATH);
		if (!StringUtils.isEmpty(imgPath))
		{
			// 设置上传图片
			user.setPhoto(imgPath);
		}
		
		try
		{
			log.info("Update user,userid is " + user.getUserId());
			userService.updateBypkSelective(user);
			log.info("Delete user old roles .");
			userRoleService.delUserRoleByUserId(user.getUserId());
			String[] roleIds = request.getParameterValues("role");
			log.info("The  user new roles id is " + roleIds);
			addUserRoleBacth(user, roleIds);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Update user failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return queryAll(request);
	}

	/**
	 * 批量添加用户角色信息
	 * <p>
	 * Title: addUserRoleBacth
	 * </p>
	 * 
	 * @param user
	 * @param roleIds
	 * @throws SQLException
	 */
	private void addUserRoleBacth(SysUser user, String[] roleIds) throws SQLException
	{
		if (!ArrayUtils.isEmpty(roleIds))
		{
			List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
			SysUserRole userRole;
			for (int i = 0; i < roleIds.length; i++)
			{
				userRole = new SysUserRole();
				userRole.setRoleId(roleIds[i]);
				userRole.setUserId(user.getUserId());
				userRole.setUserRoleId(CommonUtils.getPrimaryKey());
				userRole.setUrStatus(CommonKey.ENABLED_INT);
				userRoles.add(userRole);
			}
			log.info("Add use roles at batch.");
			userRoleService.addUserRoleBatch(userRoles);
		}
	}
}
