package cn.net.guu.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController
{

	/**
	 * 访问首页
	* <p>Title: index</p>
	* @return
	 */
	@RequestMapping("/")
	public ModelAndView index()
	{
		return new ModelAndView("index");
	}
	
	/**
	 * 访问首页
	* <p>Title: index</p>
	* @return
	 */
	@RequestMapping("/index")
	public ModelAndView index1()
	{
		return new ModelAndView("index");
	}
}
