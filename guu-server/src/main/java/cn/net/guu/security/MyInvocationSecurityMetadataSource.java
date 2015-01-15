package cn.net.guu.security;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;






import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
* <p>Title: MyInvocationSecurityMetadataSource</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年8月2日
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	
	@Resource(name="sysAuthorityServiceImpl")
	private SysAuthorityService authorityService;
	
	@Resource(name="sysResourcesServiceImpl")
	private SysResourcesService resourceService;
	
	public static Map<String, Collection<ConfigAttribute>> resourceMap=null;  
  
    public MyInvocationSecurityMetadataSource() {  
        loadResourceDefine();  
    } 
    


	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String url = ((FilterInvocation) object).getRequestUrl(); 
		System.out.println(getClass().getName() + "~~~~~~~~~" + url); 
		int firstQuestionMarkIndex = url.indexOf("?");  
		  
        if (firstQuestionMarkIndex != -1) {  
            url = url.substring(0, firstQuestionMarkIndex);  
            
        } 
        Iterator<String> ite = resourceMap.keySet().iterator();  
        while (ite.hasNext()) {  
            String resURL = ite.next();  
            if (resURL.matches(url)) {              	
                return resourceMap.get(resURL);  
            }    
        } 
		//return null;
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	

	private void loadResourceDefine() {
			
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 				
		
		List<SysAuthority> authoritieList = null;		
		
		try {
			
			authoritieList = (List<SysAuthority>) authorityService.selectByExample(null);
					
			for(SysAuthority auth :authoritieList)
			{
				ConfigAttribute ca = new SecurityConfig(auth.getAuthorityCode());
				List<SysResources> resourceList = resourceService.selectResourcesByAuthCode(auth.getAuthorityCode());
				
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
	
	

}
