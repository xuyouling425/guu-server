package cn.net.guu.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.cms.cache.WebCache;
import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.common.SystemPath;
import cn.net.guu.system.cache.AuthorityCache;
import cn.net.guu.system.cache.ConfigCache;
import cn.net.guu.system.cache.ResourceCache;


/**
 * 系统初始监听器，系统启动加载一些数据
 * 加载数据库所有的资源信息
* <p>Title: InitContextLintener</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */
public class InitContextLintener implements ServletContextListener {

	/**
	 * 系统日志
	 */
	private Log log = LogFactory.getLog(InitContextLintener.class);	
	
	/**
	 * web应用销毁时调用
	 */
	public void contextDestroyed(ServletContextEvent event) {

	}

	/**
	 * web容器启动时候调用
	 */
	public void contextInitialized(ServletContextEvent event) {
		log.info("Enter init content...");
		//初始路径
		initPath(event);
		//初始化WEBCACHE
		WebCache.getInstance().init();
		//初始化系统资源信息
		ResourceCache.getInstance().init();
		//初始化权限信息
		AuthorityCache.getInstance().init();
		//加载配置项信息
		ConfigCache.getInstance().init();
		
		//设置系统变量
		event.getServletContext().setAttribute(CommonKey.WEB_CACHE, WebCache.getWebCacheMap());
		event.getServletContext().setAttribute(CommonKey.RESOURCE_CACHE, ResourceCache.getResourceCache());
		event.getServletContext().setAttribute(CommonKey.AUTHORITY_CACHE, AuthorityCache.getAuthorityCache());
		
//		System.out.println("项目Class文件路径：" + CommKey.CLASSPATH);
//		log.info("项目部署路径：" + CommKey.WEBROOT);
//		log.info("项目Class文件路径：" + CommKey.CLASSPATH);
		
		//----------------------------初始将系统所有资源加载到内存中-------------------------------//
//		SysResourcesService resourcesService = SpringContextHolder.getBean("sysResourcesServiceImpl");
//		try {
//			//获得数据库中所有的资源信息
//			List<SysResources> resourcesList = (List<SysResources>) resourcesService.selectByExample(null);
//			if(!CommonUtils.isEmpty(resourcesList)){
//				for(SysResources resources:resourcesList){					
//					CommonKey.ResourcesMap.put(resources.getResourcesUrl(), resources);
//				}
//			}
//			
//			//数据加载到application中
//			event.getServletContext().setAttribute("ResourcesMap", CommonKey.ResourcesMap);
//			event.getServletContext().setAttribute("webCache", WebCache.getInstance());
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		log.info("Exist init content...");		
	}
	
	/**
	 * 初始项目的一些路径
	* <p>Title: initPath</p>
	* @param event
	 */
	private void initPath(ServletContextEvent event)
	{
		//项目部署路径
		SystemPath.setWebroot(event.getServletContext().getRealPath("/"));
		log.info("Porject web root path:"+event.getServletContext().getRealPath("/"));
		//项目class路径
		SystemPath.setClasspath(InitContextLintener.class.getResource("/").getPath().substring(1));
		log.info("Porject classes path:"+event.getServletContext().getRealPath("/"));
	}
}
