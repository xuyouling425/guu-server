package cn.net.guu.system.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.model.SysAuthorityExample;
import cn.net.guu.system.model.SysAuthorityExample.Criteria;
import cn.net.guu.system.model.SysAuthorityResources;
import cn.net.guu.system.service.SysAuthorityResourcesService;
import cn.net.guu.system.service.SysAuthorityService;

/**
 * 鉴权控制类 负责对权限的编辑，删除，维护，以及权限对应的资源管理
 * <p>
 * Title: SysAuthorityController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年6月26日
 */

@Controller
@RequestMapping("authority")
public class SysAuthorityController
{

	/**
	 * 鉴权接口
	 */
	@Resource(name = "sysAuthorityServiceImpl")
	private SysAuthorityService sysAuthorityService;

	/**
	 * 权限和资源 对应接口
	 */
	@Resource(name = "sysAuthorityResourcesServiceImpl")
	private SysAuthorityResourcesService authorityResourceService;

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(SysAuthorityController.class);

	/**
	 * 鉴权的相对路径
	 */
	private final String AUTHORITY_PATH = "admin/sys/authority/";

	/**
	 * 查询所有的鉴权信息
	 * <p>
	 * Title: queryAllAutority
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryAll")
	public ModelAndView queryAllAutority(HttpServletRequest request)
	{

		log.info("Entering the queryAllAutority.");
		ModelAndView mav = new ModelAndView(AUTHORITY_PATH + "authority");
		try
		{
			List<SysAuthority> authorityList = (List<SysAuthority>) sysAuthorityService.selectByExample(null);
			mav.addObject("authorityList", authorityList);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error("Query all authority faild.\n {0} ", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		log.info("Existing the queryAllAutority.");
		return mav;
	}

	/**
	 * 跳转到新增权限页面
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
		log.info("Entering the toAdd.");
		ModelAndView mav = new ModelAndView(AUTHORITY_PATH + "addAuthority");
		return mav;
	}

	/**
	 * 跳转到新增权限页面
	 * <p>
	 * Title: toAdd
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, SysAuthority authority)
	{
		log.info("Entering the add authority.");
		ModelAndView mav = new ModelAndView(AUTHORITY_PATH + "addAuthority");

		try
		{
			// 查看权限id是否已经存在
			SysAuthority au = (SysAuthority) sysAuthorityService.selectBypk(authority.getAuthorityId());

			// 权限id已经存在
			if (au != null)
			{
				log.info("The authority id has exist. Return to add authority again.");
				authority.setAuthorityStatus(CommonKey.ENABLED_INT);
				mav.addObject("authority", authority);
				return mav;
			} else
			{
				// 添加权限
				log.info("Add an authortiy.");
				sysAuthorityService.add(authority);

				// 获得复选框的资源集合
				String[] resourceIds = request.getParameterValues("resourceId");

				log.info("The select resource list is " + resourceIds);
				addAuthResBatch(authority, resourceIds);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add authority failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return queryAllAutority(request);
	}

	private void addAuthResBatch(SysAuthority authority, String[] resourceIds) throws SQLException
	{
		if (!ArrayUtils.isEmpty(resourceIds))
		// 添加权限和资源访问的对应关系
		{
			List<SysAuthorityResources> authorityResourcesList = new ArrayList<SysAuthorityResources>();
			SysAuthorityResources authorityResources;

			for (int i = 0; i < resourceIds.length; i++)
			{
				authorityResources = new SysAuthorityResources();
				authorityResources.setAuthorityId(authority.getAuthorityId());
				authorityResources.setResourcesId(resourceIds[i]);
				authorityResources.setArStatus(CommonKey.ENABLED_INT);
				authorityResources.setAuthorityResId(CommonUtils.getPrimaryKey());
				authorityResourcesList.add(authorityResources);
			}
			// 添加权限
			log.info("Add an authortiyResources.");
			authorityResourceService.addBatch(authorityResourcesList);
		}
	}

	/**
	 * 删除权限
	 * <p>
	 * Title: deleteAuthority
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView deleteAuthority(HttpServletRequest request)
	{
		log.info("Start to delete authority.");
		// 获得前台传入的主键集合
		String pids = request.getParameter("pids");
		String[] pid = pids.split("-");

		if (!ArrayUtils.isEmpty(pid))
		{
			List<String> auList = Arrays.asList(pid);

			// 设置删除的条件。
			SysAuthorityExample example = new SysAuthorityExample();
			Criteria criteria = example.createCriteria();
			criteria.andAuthorityIdIn(auList);

			try
			{
				// 删除权限
				sysAuthorityService.deleteByExample(example);
				// 删除权限和资源的对应关系
				log.info("Start to delete authortiyResources.");
				authorityResourceService.delBatchByAuthorityIds(auList);

			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Delete authority failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		}

		return queryAllAutority(request);
	}

	/**
	 * 跳转到修改权限页面
	 * <p>
	 * Title: toUpdateAuthority
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdateAuthority(HttpServletRequest request)
	{

		String authorityId = request.getParameter("pids");
		ModelAndView mav = new ModelAndView(AUTHORITY_PATH + "updateAuthority");
		try
		{
			log.info("Start to query authortiy.The authority is " + authorityId);
			SysAuthority authority = (SysAuthority) sysAuthorityService.selectBypk(authorityId);
			mav.addObject("authority", authority);
			// 查询权限和资源的对应关系
			log.info("Start to query authorityResources.");
			List<SysAuthorityResources> auResourcesList = authorityResourceService.queryAuResourcesByAuId(authorityId);
			mav.addObject("auResList", auResourcesList);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Query authority is failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return mav;
	}

	/**
	 * 更新权限信息
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param request
	 * @param authority
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, SysAuthority authority)
	{

		try
		{
			// 添加权限
			log.info("Update an authortiy.");
			sysAuthorityService.updateBypkSelective(authority);
			log.info("Delete authority resources,the authority is " + authority.getAuthorityId());

			authorityResourceService.delByAuthorityId(authority.getAuthorityId());

			// 获得复选框的资源集合
			String[] resourceIds = request.getParameterValues("resourceId");

			log.info("The select resource list is " + resourceIds);
			
			addAuthResBatch(authority, resourceIds);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add authority failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return queryAllAutority(request);
	}
}
