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

import cn.net.guu.cms.model.About;
import cn.net.guu.cms.service.AboutService;
import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.UploadUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(AboutController.class);

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
		log.info("Entering addModify().");
		ModelAndView mav = new ModelAndView("admin/about");
		List<String> imgList = UploadUtils.uploadFiles(request,CommonKey.UPLOAD_IMAGE_PATH);
		// 上传成功，返回image路径设置为about的图片路径
		if (!CommonUtils.isEmpty(imgList))
		{
			about.setImagePath(imgList.get(0));
		}
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
			log.info("The current about=[" + new ObjectMapper().writeValueAsString(about) + "]");
		} catch (Exception e)
		{
			log.error("Add or modify about faild.", e);
			// TODO: handle exception
			return new ModelAndView("admin/error");
		}
		log.info("Exiting addModify().");
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
	@RequestMapping("/selAbout")
	public ModelAndView selAbout(HttpServletRequest request)
	{
		log.info("Entering selAbout().");
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
			log.info("The current about is " + about + "");
			mav.addObject("about", about);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Select about Faild.", e);
			return new ModelAndView("admin/error");
		}
		log.info("Exiting selAbout().");
		return mav;
	}
}
