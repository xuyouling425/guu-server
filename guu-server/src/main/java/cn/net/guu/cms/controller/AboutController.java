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
		try
		{
			// 判定aboutId是否为空,aboutId为空，添加一条数据
			if (StringUtils.isEmpty(about.getAboutId()))
			{
				aboutService.addSelective(about);
			} else
			{
				// aboutId不为空表示为修改
				aboutService.updateBypkSelective(about);
			}

		} catch (Exception e)
		{
			// TODO: handle exception
			return new ModelAndView("admin/error");
		}

		return new ModelAndView("admin/about");
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
		ModelAndView maw = new ModelAndView("admin/about");
		try
		{
			@SuppressWarnings("unchecked")
			List<About> aboutList = (List<About>) aboutService.selectByExample(null);
			//如果获得aboutList集合不为空，则去第一个about消息
			if (null != aboutList && aboutList.size() > 0)
			{
				about = aboutList.get(0);
			}
			maw.addObject("about", about);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("admin/error");
		}

		return maw;
	}
}
