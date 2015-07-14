package cn.net.guu.core.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置系统路径变量
 * <p>
 * Title: SystemPath
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2014年7月29日
 */
public class SystemPath
{

	/**
	 * 启动的时候，系统中所有的资源信息添加到map中
	 */
	public static Map<String, Object> ResourcesMap = new HashMap<String, Object>();

	/**
	 * 项目部署的路径
	 */
	public static String webroot;
	/**
	 * 项目部署文件路径
	 */
	public static String classpath;

	public static Map<String, Object> getResourcesMap()
	{
		return ResourcesMap;
	}

	public static void setResourcesMap(Map<String, Object> resourcesMap)
	{
		ResourcesMap = resourcesMap;
	}

	public static String getWebroot()
	{
		return webroot;
	}

	public static void setWebroot(String webroot)
	{
		SystemPath.webroot = webroot;
	}

	public static String getClasspath()
	{
		return classpath;
	}

	public static void setClasspath(String classpath)
	{
		SystemPath.classpath = classpath;
	}

}
