package cn.net.guu.system.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.net.guu.system.model.SysRole;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.service.SysRoleService;
import cn.net.guu.system.service.SysUserService;

/**
 * 用户controller ,处理用户的相关业务逻辑信息
* <p>Title: SysUserController</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月24日
 */


@Controller
@RequestMapping("/user")
public class SysLoginController {
	
	/**
	 * 用户接口
	 */
	@Resource(name="sysUserServiceImpl")
	private SysUserService userService;
	
	/**
	 * 角色接口
	 */
	@Resource(name="sysRoleServiceImpl")
	private SysRoleService roleService;
	
	/**
	 * 后台用户登录
	 * 只允许post提交的登录请求
	* <p>Title: login</p>
	* <p>Description: </p>
	* @param request
	* @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(SysUser loginUser,HttpServletRequest request){
		//默认后台登录成功跳转页面
		String returnUrl = "redirect:/server/index.jsp";
		
		try {
			SysUser user = userService.userLogin(loginUser.getLoginName(), loginUser.getLoginPassword());
			if(user==null){
				//登录失败，到登录页面
				returnUrl = "redirect:/server/login.jsp";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnUrl;						
	}
	
	/**
	 * 后台用户退出
	* <p>Title: logout</p>
	* <p>Description: </p>
	* @param request
	* @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){

		return  "redirect:/server/login.jsp";
	}
}
