package cn.net.guu.system.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.spring.SpringContextHolder;
import cn.net.guu.system.model.SysResources;
import cn.net.guu.system.model.SysResourcesExample;
import cn.net.guu.system.model.SysResourcesExample.Criteria;
import cn.net.guu.system.service.SysResourcesService;

/**
 * 系统启动将资源表中的所有数据加载到缓存中
 * <p>
 * Title: ResourceCache
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年7月14日
 */
public class ResourceCache
{

	/**
	 * 日志
	 */
	private Log log = LogFactory.getLog(ResourceCache.class);

	/**
	 * ResourceCache
	 */
	private static ResourceCache instance;

	/**
	 * resourceCache
	 */
	private static  List<SysResources> resourceCache = new ArrayList<SysResources>();

	public static List<SysResources> getResourceCache()
	{
		return resourceCache;
	}

	/**
	 * 私有构造函数
	 * <p>
	 * Title:
	 * </p>
	 */
	private ResourceCache()
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
	public static synchronized ResourceCache getInstance()
	{
		if (null == instance)
		{
			instance = new ResourceCache();
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
		log.info("Start to init the ResourceCache...");
		resCache();
		log.info("End to init the ResourceCache...");
	}

	/**
	 * 查询所有的资源信息
	* <p>Title: refreshResourceCache</p>
	 */
	private void resCache()
	{
		log.info("Start to refresh the ResourceCache...");
		//先清除缓存，在添加
		resourceCache.clear();
		
		SysResourcesService service = SpringContextHolder.getBean("sysResourcesServiceImpl");
		SysResourcesExample example = new SysResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andResStatusEqualTo(CommonKey.ENABLED_INT);
		example.setOrderByClause(" resources_type desc ");

		try
		{
			List<SysResources> resources = (List<SysResources>) service.selectByExample(example);
			resourceCache = resources;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Init the ResourceCache failed.", e);
		}

		log.info("End to refresh the ResourceCache...");
	}

}
