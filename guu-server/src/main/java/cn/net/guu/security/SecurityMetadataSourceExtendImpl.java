package cn.net.guu.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;

import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.model.SysResources;
import cn.net.guu.system.service.SysAuthorityService;
import cn.net.guu.system.service.SysResourcesService;

/**
 * 初始化时加载权限与资源的对应关系
 * 
 * @author shadow
 * 
 * @create 2012.04.28
 */
public class SecurityMetadataSourceExtendImpl implements SecurityMetadataSourceExtend
{

	private Logger logger = Logger.getLogger(SecurityMetadataSourceExtendImpl.class);

	/**
	 * 资源接口
	 */
	@Resource(name = "sysResourcesServiceImpl")
	private SysResourcesService resourcesService;

	/**
	 * 权限接口
	 */
	@Resource(name = "sysAuthorityServiceImpl")
	private SysAuthorityService authorityService;

	private SessionRegistry sessionRegistry; // session库存

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null; // 资源集合

	public SessionRegistry getSessionRegistry()
	{
		return sessionRegistry;
	}

	@Resource(name = "sessionRegistry")
	public void setSessionRegistry(SessionRegistry sessionRegistry)
	{
		this.sessionRegistry = sessionRegistry;
	}

	public boolean supports(Class<?> clazz)
	{
		return true;
	}

	// 初始化方法时候从数据库中读取资源
	@PostConstruct
	public void init()
	{
		load();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet())
		{
			attributes.addAll(entry.getValue());
		}
		return attributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
	{
		HttpServletRequest request = ((FilterInvocation) object).getRequest();

		logger.info("Current request url is " + request.getRequestURI());

		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();
		
		int firstQuestionMarkIndex = url.indexOf("?");

		//去掉后面的参数
		if (firstQuestionMarkIndex != -1)
		{
			url = url.substring(0, firstQuestionMarkIndex);
		}
		//去掉前面的“/”
		if(url.startsWith("/"))
		{
			url = url.substring(1);
		}

		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext())
		{
			String resURL = ite.next();
			// 判定是否包含该请求的url
			// 一个资源里面有可能包含2个url地址。
			if (resURL.indexOf(url) != -1)
			{
				logger.info("Find the url in the resouceMap. ");
				return resourceMap.get(resURL);
			}
		}
		logger.info("Not find the url in the resouceMap.");
		return null;
	}

	/**
	 * 加载所有资源与权限的关系
	 */
	public void load()
	{

		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		try
		{
			logger.info("Get all authoritieList....");
			List<SysAuthority> authoritieList = (List<SysAuthority>) authorityService.selectByExample(null);
			for (SysAuthority auth : authoritieList)
			{
				ConfigAttribute ca = new SecurityConfig(auth.getAuthorityId());
				logger.info("Query authory resources,the authority id is " + auth.getAuthorityId());
				// 通过权限编码，获得对应所有的资源
				List<SysResources> resourceList = resourcesService.selectResourcesByAuthId(auth.getAuthorityId());

				for (SysResources re : resourceList)
				{
					/*
					 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。 sparta
					 */
					String url = re.getResourcesUrl();
					if (resourceMap.containsKey(url))
					{
						Collection<ConfigAttribute> value = resourceMap.get(url);
						value.add(ca);
						resourceMap.put(url, value);
						logger.info("The reourceMap contain the key [" + url + "], put the canfig [" + ca.getAttribute() + "] in the url.");
					} else
					{
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
						atts.add(ca);
						resourceMap.put(url, atts);
						logger.info("The reourceMap not contain the key [" + url + "], add a new url in zhe resource map. The config ["
								+ ca.getAttribute() + "] .");
					}
				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("Load the Collection<ConfigAttribute> failed.", e);
		} finally
		{

		}

	}

	public void expireNow()
	{
		resourceMap.clear();
		load();
		shotOff();
	}

	// ** 把全部用户踢出系统,必须重新登录 *//*
	private void shotOff()
	{
		List<Object> users = sessionRegistry.getAllPrincipals();
		if (logger.isDebugEnabled())
			logger.debug("当前用户数: " + users.size());
		// 遍历所有用户
		for (Object o : users)
		{
			if (logger.isDebugEnabled())
			{
				UserDetails user = (UserDetails) o;
				logger.debug("当前用户名: " + user.getUsername());
			}
			for (SessionInformation information : sessionRegistry.getAllSessions(o, false))
			{
				information.expireNow();
				sessionRegistry.removeSessionInformation(information.getSessionId());
			}
		}
	}
}