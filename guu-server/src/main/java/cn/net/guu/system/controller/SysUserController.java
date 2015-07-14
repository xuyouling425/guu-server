package cn.net.guu.system.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.service.SysRoleService;
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

}
