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
import cn.net.guu.cms.model.Business;
import cn.net.guu.cms.service.BusinessService;
import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.UploadUtils;

/**
 * 服务接口类
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
@RequestMapping("/business")
public class BusinessController
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(BusinessController.class);

	@Resource(name = "businessServiceImpl")
	private BusinessService businessService;

	/**
	 * 查询出所有的服务（业务）信息
	 * <p>
	 * Title: queryBusiness
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	public ModelAndView queryBusiness()
	{
		log.info("Entering queryBusiness()..");
		ModelAndView mav = new ModelAndView("admin/business");
		try
		{
			List<Business> businesses = (List<Business>) businessService.selectByExample(null);
			mav.addObject("businesses", businesses);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Select query business Faild.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		log.info("Exiting queryBusiness()..");
		return mav;
	}

	/**
	 * 新增一个服务
	 * <p>
	 * Title: addBusiness
	 * </p>
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addBusiness(HttpServletRequest request, Business business)
	{
		log.info("Entering addBusiness()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		// 设置上传图片
		business.setImage(imgPath);
		// 设置主键
		business.setId(CommonUtils.getPrimaryKey());

		try
		{

			// 调用添加接口
			businessService.add(business);
			//添加成功后刷新缓存
			WebCache.getInstance().refreshBusiness();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add Bussiness Failed.", e);
		}

		log.info("Exiting addBusiness()..");
		return queryBusiness();
	}

	/**
	 * 跳转到添加服务页面
	 * <p>
	 * Title: toAddBuss
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAddBus()
	{
		return new ModelAndView("admin/addBusiness");
	}

	/**
	 * 跳转到修改服务页面
	 * <p>
	 * Title: toAddBuss
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request)
	{
		String pid = request.getParameter("pid");
		ModelAndView mav = new ModelAndView("admin/updateBusiness");
		Business business = new Business();
		if (!StringUtils.isEmpty(pid))
		{
			try
			{
				business = (Business) businessService.selectBypk(pid);

				mav.addObject("business", business);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Select Business Failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		} else
		{
			// pid传值为空，直接返回查看页面
			return queryBusiness();
		}

		return mav;
	}

	/**
	 * 新增一个服务
	 * <p>
	 * Title: addBusiness
	 * </p>
	 * 
	 * @param request
	 * @param business
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView updateBusiness(HttpServletRequest request, Business business)
	{
		log.info("Entering updateBussiness()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		if (!StringUtils.isEmpty(imgPath))
		{
			// 设置上传图片
			business.setImage(imgPath);
		}
		
		try
		{
			// 更新接口
			businessService.updateByPrimaryKeyWithBLOBs(business);
			//刷新缓存
			WebCache.getInstance().refreshBusiness();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Update Bussiness Failed.", e);
		}

		log.info("Exiting updateBussiness()..");
		return queryBusiness();
	}

	/**
	 * 删除一个 服务信息
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
				businessService.deleteBypk(pid);
				
				//刷新缓存
				WebCache.getInstance().refreshBusiness();
				
			} catch (SQLException e)
			{
				// CommonKey.ADMIN_ERROR_URL
				log.error("Delete Business Failed.", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		}
		return queryBusiness();
	}
}
