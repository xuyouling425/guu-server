package cn.net.guu.system.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import cn.net.guu.system.model.SysRoleExample;
import cn.net.guu.system.model.SysRoleExample.Criteria;
import cn.net.guu.system.service.SysRoleAuthorityService;
import cn.net.guu.system.service.SysRoleService;

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
	 * 跳转到新增角色页面
	 * <p>
	 * Title: toAddRole
	 * </p>
	 * 
	 * @param request
	 * @param role
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAddRole(HttpServletRequest request, SysRole role)
	{
		ModelAndView mav = new ModelAndView(ROLE_PATH + "addRole");
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
				role.setCreatTime(new Date());
				role.setRoleStatus(CommonKey.ENABLED_INT);
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
	@RequestMapping("/update")
	public ModelAndView updateRole(HttpServletRequest request, SysRole role)
	{
		try
		{
			log.info("Udapte the role.");
			roleService.updateBypkSelective(role);
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

	/**
	 * 跳转到更新页面
	 * <p>
	 * Title: toUpdate
	 * </p>
	 * 
	 * @param request
	 * @param role
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request)
	{
		String roleId = request.getParameter("pids");
		ModelAndView mav = new ModelAndView(ROLE_PATH + "updateRole");
		try
		{
			// 查询角色信息
			log.info("Query the role,the roleId is " + roleId);
			SysRole role = (SysRole) roleService.selectBypk(roleId);
			mav.addObject("role", role);
			// 查询 角色和权限 的对应关系
			log.info("Query the roleAuthority,the roleId is " + roleId);
			List<SysRoleAuthority> roleAuthorities = roleAuthorityService.selectRoleAuthorityByRoleId(roleId);
			mav.addObject("roleAuthorityList", roleAuthorities);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Query role failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return mav;
	}

	/**
	 * 删除角色
	 * <p>
	 * Title: deleteRole
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView deleteRole(HttpServletRequest request)
	{

		// 获得前台传入的主键集合
		String pids = request.getParameter("pids");
		String[] pid = pids.split("-");

		if (!ArrayUtils.isEmpty(pid))
		{
			log.info("Delete roles where roleId in " + pid);
			List<String> roleIdList = Arrays.asList(pid);
			SysRoleExample example = new SysRoleExample();
			Criteria criteria = example.createCriteria();
			criteria.andRoleIdIn(roleIdList);
			try
			{
				roleService.deleteByExample(example);
				
				//删除对应关系
				log.info("Delete role-authority .");
				roleAuthorityService.delByRoleIds(roleIdList);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Delete role failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		}

		return queryAll(request);
	}
}
