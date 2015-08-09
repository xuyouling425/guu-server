package cn.net.guu.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.model.SysResources;
import cn.net.guu.system.service.SysAuthorityService;
import cn.net.guu.system.service.SysResourcesService;

/**
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * <p>
 * Title: MyInvocationSecurityMetadataSource
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2014年8月2日
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{

	private Logger logger = Logger.getLogger(MyInvocationSecurityMetadataSource.class);

	@Resource(name = "sysAuthorityServiceImpl")
	private SysAuthorityService authorityService;

	@Resource(name = "sysResourcesServiceImpl")
	private SysResourcesService resourceService;

	public static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MyInvocationSecurityMetadataSource()
	{
		loadResourceDefine();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
	{
		// TODO Auto-generated method stub
		String url = ((FilterInvocation) object).getRequestUrl();
		logger.info("The url is " + url);
		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1)
		{
			url = url.substring(0, firstQuestionMarkIndex);

		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext())
		{
			String resURL = ite.next();
			//resourceMap 有可能会有2个url的情况
			if (resURL.indexOf(url) != -1)
			{
				logger.info("Find the url in zhe resourceMap .");
				return resourceMap.get(resURL);
			}
		}
		// return null;
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz)
	{
		// TODO Auto-generated method stub
		return true;
	}

	private void loadResourceDefine()
	{

		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		List<SysAuthority> authoritieList = null;

		try
		{

			authoritieList = (List<SysAuthority>) authorityService.selectByExample(null);

			for (SysAuthority auth : authoritieList)
			{
				ConfigAttribute ca = new SecurityConfig(auth.getAuthorityCode());
				List<SysResources> resourceList = resourceService.selectResourcesByAuthId(auth.getAuthorityCode());

				logger.info("The authority name is " + auth.getAuthorityName());
				for (SysResources re : resourceList)
				{
					String url = re.getResourcesUrl();
					logger.info("The resource is " + url);
					if (resourceMap.containsKey(url))
					{
						Collection<ConfigAttribute> value = resourceMap.get(url);
						value.add(ca);
						resourceMap.put(url, value);
						logger.info("Find the url in the resourceMap.The key url is " + url + " the value is " + resourceMap.get(url));
					} else
					{
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
						atts.add(ca);
						resourceMap.put(url, atts);
						logger.info("Can not find the url in the resourceMap,add a new map.The key url is " + url + " the value is " + resourceMap.get(url));
					}
				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{

		}

	}

}
