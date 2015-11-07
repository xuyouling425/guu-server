package cn.net.guu.system.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.cache.ConfigCache;
import cn.net.guu.system.model.Config;

/**
 * 配置项控制类
 * <p>
 * Title: ConfigController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年11月7日
 */

@Controller
@RequestMapping("/config")
public class ConfigController
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(ConfigController.class);

	/**
	 * 配置项接口
	 */
	@Resource(name = "configServiceImpl")
	private BaseService configService;

	@RequestMapping("/updateConfig")
	public ModelAndView updateConfig(HttpServletRequest request, Config config)
	{
		log.info("start to update config,the configId is " + config.getConfigId());

		try
		{
			// 更新配置项
			int temp = configService.updateBypkSelective(configService);
			if (temp > 0)
			{
				// 刷新配置项缓存
				ConfigCache.getInstance().refreshConfigCache();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block

		}

		return new ModelAndView();
	}
}
