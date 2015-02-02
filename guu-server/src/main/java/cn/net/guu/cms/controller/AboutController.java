package cn.net.guu.cms.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.cms.model.About;
import cn.net.guu.cms.service.AboutService;
import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.UploadUtils;

/**
 * about 控制器
 * <p>
 * Title: AboutController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年1月31日
 */
@Controller
@RequestMapping("/about")
public class AboutController
{

	@Resource(name = "aboutServiceImpl")
	private AboutService aboutService;

	/**
	 * 新增或修改about 信息
	 * <p>
	 * Title: addModify
	 * </p>
	 * 
	 * @param request
	 * @param about
	 * @return
	 */
	@RequestMapping("/addModify")
	public ModelAndView addModify(HttpServletRequest request, About about)
	{
		ModelAndView mav = new ModelAndView("admin/about");
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		// 判断 request 是否有文件上传,即多部分请求
//		if (multipartResolver.isMultipart(request))
//		{
			UploadUtils.uploadFiles(request, CommonKey.getWebroot() + "/resources/images/uploads/");
//		}
		try
		{
			// 判定aboutId是否为空,aboutId为空，添加一条数据
			if (StringUtils.isEmpty(about.getAboutId()))
			{
				about.setAboutId(CommonUtils.getPrimaryKey());
				aboutService.addSelective(about);
			} else
			{
				// aboutId不为空表示为修改
				aboutService.updateBypkSelective(about);
			}

			mav.addObject("about", about);
		} catch (Exception e)
		{
			// TODO: handle exception
			return new ModelAndView("admin/error");
		}

		return mav;
	}

	/**
	 * 后台管理员查看 about 信息
	 * <p>
	 * Title: selAbout
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selAbout")
	public ModelAndView selAbout(HttpServletRequest request)
	{
		// 创建一个about对象
		About about = new About();
		ModelAndView mav = new ModelAndView("admin/about");
		try
		{
			@SuppressWarnings("unchecked")
			List<About> aboutList = (List<About>) aboutService.selectByExample(null);
			// 如果获得aboutList集合不为空，则去第一个about消息
			if (null != aboutList && aboutList.size() > 0)
			{
				about = aboutList.get(0);
			}

			mav.addObject("about", about);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("admin/error");
		}

		return mav;
	}
}
