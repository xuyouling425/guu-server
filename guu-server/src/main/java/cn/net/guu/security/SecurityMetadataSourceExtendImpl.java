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
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RegexRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;


import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.model.SysResources;
import cn.net.guu.system.service.SysAuthorityService;
import cn.net.guu.system.service.SysResourcesService;
import cn.net.guu.system.service.SysRoleService;


/**
 * 初始化时加载权限与资源的对应关系
 * 
 * @author shadow

 * @create 2012.04.28
 */
public class SecurityMetadataSourceExtendImpl implements
		SecurityMetadataSourceExtend {

	protected Logger logger = Logger.getLogger(getClass());

	/**
	 * 权限接口
	 */
	@Resource(name="sysAuthorityServiceImpl")
	private SysAuthorityService authorityService; 

	/**
	 * 资源接口
	 */
	@Resource(name="sysResourcesServiceImpl")
	private SysResourcesService resourcesService; 

	private RequestMatcher requestMatcher; // 匹配规则

	private String matcher; // 规则标识

	private SessionRegistry sessionRegistry; // session库存

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null; // 资源集合


	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	@Resource(name = "sessionRegistry")
	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	// 初始化方法时候从数据库中读取资源
	@PostConstruct
	public void init() {
		load();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap
				.entrySet()) {
			attributes.addAll(entry.getValue());
		}
		return attributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		HttpServletRequest request = ((FilterInvocation) object).getRequest();

		// DEBUG模式显示请求资源路径
		if (logger.isDebugEnabled())
			logger.debug("当前请求URL: " + request.getRequestURI());

		// 检测请求与当前资源匹配的正确性
		Iterator<String> iterator = resourceMap.keySet().iterator();
		while (iterator.hasNext()) {
			String uri = iterator.next();
			if (matcher.toLowerCase().equals("ant")) {
				requestMatcher = new AntPathRequestMatcher(uri);
			}
			if (matcher.toLowerCase().equals("regex")) {
				requestMatcher = new RegexRequestMatcher(uri, request
						.getMethod(), true);
			}
			if (requestMatcher.matches(request))
				return resourceMap.get(uri);
		}
		return null;
	}

	/**
	 * 加载所有资源与权限的关系
	 */
	@SuppressWarnings("unchecked")
	public void load() {
		
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 				
		
		List<SysAuthority> authoritieList = null;		
		
		try {
			//获得所有的权限
			authoritieList = (List<SysAuthority>) authorityService.selectByExample(null);
					
			for(SysAuthority auth :authoritieList)
			{
				ConfigAttribute ca = new SecurityConfig(auth.getAuthorityCode());
				//通过权限编码，获得对应所有的资源
				List<SysResources> resourceList = resourcesService.selectResourcesByAuthCode(auth.getAuthorityCode());
				
				System.out.println("*******************************"+auth.getAuthorityName());
				for(SysResources re :resourceList)
				{
					String url = re.getResourcesUrl();
					System.out.println("*******************************"+url);
					if (resourceMap.containsKey(url)) {
						 Collection<ConfigAttribute> value = resourceMap.get(url);
						 value.add(ca);
						 resourceMap.put(url, value);
						 System.out.println("key url"+url+":"+resourceMap.get(url));
					}else
					{
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();  
	                    atts.add(ca);  
	                    resourceMap.put(url, atts); 
	                    System.out.println("key url"+url+":"+resourceMap.get(url));
					}
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}

	
	

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public void expireNow() {
		resourceMap.clear();
		load();
		shotOff();
	}

	//** 把全部用户踢出系统,必须重新登录 *//*
	private void shotOff() {
		List<Object> users = sessionRegistry.getAllPrincipals();
		if (logger.isDebugEnabled())
			logger.debug("当前用户数: " + users.size());
		// 遍历所有用户
		for (Object o : users) {
			if (logger.isDebugEnabled()) {
				UserDetails user = (UserDetails) o;
				logger.debug("当前用户名: " + user.getUsername());
			}
			for (SessionInformation information : sessionRegistry
					.getAllSessions(o, false)) {
				information.expireNow();
				sessionRegistry.removeSessionInformation(information
						.getSessionId());
			}
		}
	}
}