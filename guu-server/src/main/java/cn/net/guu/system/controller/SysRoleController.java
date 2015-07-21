package cn.net.guu.system.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.system.model.SysRole;
import cn.net.guu.system.model.SysRoleAuthority;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.service.SysRoleAuthorityService;
import cn.net.guu.system.service.SysRoleService;
import cn.net.guu.system.service.SysUserService;

/**
 * 角色管理
 * <p>
 * Title: SysRoleController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年7月21日
 */
@Controller
@RequestMapping("/role")
public class SysRoleController
{

	/**
	 * 角色接口
	 */
	@Resource(name = "sysRoleServiceImpl")
	private SysRoleService roleService;

	/**
	 * 角色-权限 接口
	 */
	@Resource(name = "sysRoleAuthorityServiceImpl")
	private SysRoleAuthorityService roleAuthorityService;
	/**
	 * 角色管理的相对路径
	 */
	private final String ROLE_PATH = "admin/sys/role/";

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(SysRoleController.class);

	/**
	 * 查询出所有的角色信息
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

		ModelAndView mav = new ModelAndView(ROLE_PATH + "role");
		try
		{
			log.info("Query all roles.");
			List<SysRole> roleList = (List<SysRole>) roleService.selectByExample(null);
			mav.addObject("roleList", roleList);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Query role failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return mav;
	}

	/**
	 * 添加一个角色
	 * <p>
	 * Title: addRole
	 * </p>
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addRole(HttpServletRequest request, SysRole role)
	{
		ModelAndView mav = new ModelAndView(ROLE_PATH + "addRole");

		try
		{
			log.info("Query role by roleid=" + role.getRoleId());
			SysRole sRole = (SysRole) roleService.selectBypk(role.getRoleId());
			if (sRole != null)
			{
				log.info("Role has exist.Return to add role.");
				mav.addObject("role", role);
				return mav;
			} else
			{
				log.info("Add a role.");
				roleService.add(role);

				// 获得复选框的资源集合
				String[] authorityIds = request.getParameterValues("authorityId");
				log.info("The select authority list is " + authorityIds);

				addRoleAuthBatch(role, authorityIds);
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add role failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return queryAll(request);
	}

	private void addRoleAuthBatch(SysRole role, String[] authorityIds) throws SQLException
	{
		if (!ArrayUtils.isEmpty(authorityIds))
		{
			List<SysRoleAuthority> roleAuthorities = new ArrayList<SysRoleAuthority>();
			SysRoleAuthority roleAuthority;
			for (int i = 0; i < authorityIds.length; i++)
			{
				roleAuthority = new SysRoleAuthority();
				roleAuthority.setAuthorityId(authorityIds[i]);
				roleAuthority.setRoleId(role.getRoleId());
				roleAuthority.setRoleAuthorityId(CommonUtils.getPrimaryKey());
				roleAuthority.setRaStatus(CommonKey.ENABLED_INT);
				roleAuthorities.add(roleAuthority);
			}

			log.info("Add role authority list.");
			roleAuthorityService.addBatch(roleAuthorities);
		}
	}

	/**
	 * 更新一个角色信息
	 * 
	 * <p>
	 * Title: updateRole
	 * </p>
	 * 
	 * @param request
	 * @param role
	 * @return
	 */
	public ModelAndView updateRole(HttpServletRequest request, SysRole role)
	{
		try
		{
			log.info("Udapte the role.");
			roleService.updateBypk(role);
			log.info("Delte role authority by roleId,the roleId is " + role.getRoleId());
			roleAuthorityService.delByRoleId(role.getRoleId());

			// 获得复选框的资源集合
			String[] authorityIds = request.getParameterValues("authorityId");
			log.info("The select authority list is " + authorityIds);

			addRoleAuthBatch(role, authorityIds);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Update role failed .", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return queryAll(request);
	}

}
