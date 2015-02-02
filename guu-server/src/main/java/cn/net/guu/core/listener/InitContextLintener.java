package cn.net.guu.core.listener;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.spring.SpringContextHolder;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.system.model.SysResources;
import cn.net.guu.system.service.SysResourcesService;


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

	private Log log = LogFactory.getLog(InitContextLintener.class);	
	
	/**
	 * web应用销毁时调用
	 */
	public void contextDestroyed(ServletContextEvent event) {

	}

	/**
	 * web容器启动时候调用
	 */
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent event) {
		//项目部署路径
		CommonKey.setWebroot(event.getServletContext().getRealPath("/"));
		//项目class路径
		CommonKey.setClasspath(InitContextLintener.class.getResource("/").getPath().substring(1));
		
		System.out.println("项目部署路径：" + event.getServletContext().getRealPath("/"));
//		System.out.println("项目Class文件路径：" + CommKey.CLASSPATH);
//		log.info("项目部署路径：" + CommKey.WEBROOT);
//		log.info("项目Class文件路径：" + CommKey.CLASSPATH);
		
		//----------------------------初始将系统所有资源加载到内存中-------------------------------//
		SysResourcesService resourcesService = SpringContextHolder.getBean("sysResourcesServiceImpl");
		try {
			//获得数据库中所有的资源信息
			List<SysResources> resourcesList = (List<SysResources>) resourcesService.selectByExample(null);
			if(!CommonUtils.isEmpty(resourcesList)){
				for(SysResources resources:resourcesList){					
					CommonKey.ResourcesMap.put(resources.getResourcesUrl(), resources);
				}
			}
			//数据加载到application中
			event.getServletContext().setAttribute("ResourcesMap", CommonKey.ResourcesMap);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
