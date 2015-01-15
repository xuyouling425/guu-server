package cn.net.guu.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置公共的变量，
 * 继承ICommonKey ,可以得到公共的常量
* <p>Title: CommonKey</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */
public  class CommonKey implements ICommonKey {
	
	/**
	 * 启动的时候，系统中所有的资源信息添加到map中
	 */
	public static Map<String,Object> ResourcesMap = new HashMap<String,Object>();	
	
	/**
	 * 项目部署的路径
	 */
	public static  String webroot  ;
	/**
	 * 项目部署文件路径
	 */
	public static String classpath ;
	
	public static Map<String, Object> getResourcesMap() {
		return ResourcesMap;
	}
	public static void setResourcesMap(Map<String, Object> resourcesMap) {
		ResourcesMap = resourcesMap;
	}
	public static String getWebroot() {
		return webroot;
	}
	public static void setWebroot(String webroot) {
		CommonKey.webroot = webroot;
	}
	public static String getClasspath() {
		return classpath;
	}
	public static void setClasspath(String classpath) {
		CommonKey.classpath = classpath;
	}

	


	
}
