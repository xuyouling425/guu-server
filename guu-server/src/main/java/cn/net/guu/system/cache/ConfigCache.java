package cn.net.guu.system.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.service.BaseService;
import cn.net.guu.core.spring.SpringContextHolder;
import cn.net.guu.system.model.Config;
import cn.net.guu.system.model.ConfigExample;
import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.model.SysAuthorityExample;
import cn.net.guu.system.model.SysAuthorityExample.Criteria;
import cn.net.guu.system.service.SysAuthorityService;

/**
 * 配置项缓存
 * 
 * <p>
 * Title: ConfigCache
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
public class ConfigCache
{

	/**
	 * 日志
	 */
	private Log log = LogFactory.getLog(ConfigCache.class);

	/**
	 * ConfigCache
	 */
	private static ConfigCache instance;

	/**
	 * configCache
	 */
	private static Map<String, Config> configCache = new HashMap<String, Config>();

	/**
	 * 私有构造函数
	 * <p>
	 * Title:
	 * </p>
	 */
	private ConfigCache()
	{

	}

	/**
	 * 资源缓存使用单例
	 * <p>
	 * Title: getInstance
	 * </p>
	 * 
	 * @return
	 */
	public static synchronized ConfigCache getInstance()
	{
		if (null == instance)
		{
			instance = new ConfigCache();
		}
		return instance;
	}

	/**
	 * 初始所有资源信息
	 * <p>
	 * Title: init
	 * </p>
	 */
	public void init()
	{
		log.info("Start to init the config cache...");
		refreshConfigCache();
		log.info("End to init the config Cache...");
	}

	/**
	 * 查询所有的权限信息
	 * <p>
	 * Title: refreshResourceCache
	 * </p>
	 */
	public void refreshConfigCache()
	{
		log.info("Start to refresh the config cache...");
		
		//先清除缓存，在添加
		configCache.clear();
		
		BaseService service = SpringContextHolder.getBean("configServiceImpl");
		try
		{
			// 查询所有的配置项
			List<Config> configs = (List<Config>) service.selectByExample(null);

			for (Config config : configs)
			{
				configCache.put(config.getConfigId(), config);
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("refresh the config cache failed.", e);
		}

		log.info("End to refresh the congif cache...");
	}

	/**
	 * 通过 configId 获得对应的配置项
	* <p>Title: getConfigValue</p>
	* @param configId
	* @return
	 */
	public String getConfigValue(String configId)
	{
		String conValue = "";
		Config config = configCache.get(configId);
		if (null != config)
		{
			conValue = StringUtils.isEmpty(config.getConfigValue()) ? config.getDefaultValue() : config.getConfigValue();
		}
		return conValue;
	}
	
}
