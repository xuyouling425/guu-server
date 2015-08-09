package cn.net.guu.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
/**  
 *AccessdecisionManager在Spring security中是很重要的。  
 *  
 *在验证部分简略提过了，所有的Authentication实现需要保存在一个GrantedAuthority对象数组中。   
 *这就是赋予给主体的权限。 GrantedAuthority对象通过AuthenticationManager  
 *保存到 Authentication对象里，然后从AccessDecisionManager读出来，进行授权判断。   
 *  
 *Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。   
 *一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。   
 *这个 AccessDecisionManager 被AbstractSecurityInterceptor调用，  
 *它用来作最终访问控制的决定。 这个AccessDecisionManager接口包含三个方法：   
 *  
 void decide(Authentication authentication, Object secureObject,  
    List<ConfigAttributeDefinition> config) throws AccessDeniedException;  
 boolean supports(ConfigAttribute attribute);  
 boolean supports(Class clazz);  
   
  从第一个方法可以看出来，AccessDecisionManager使用方法参数传递所有信息，这好像在认证评估时进行决定。   
  特别是，在真实的安全方法期望调用的时候，传递安全Object启用那些参数。   
  比如，让我们假设安全对象是一个MethodInvocation。   
  很容易为任何Customer参数查询MethodInvocation，  
  然后在AccessDecisionManager里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。   
  如果访问被拒绝，实现将抛出一个AccessDeniedException异常。  
  
  这个 supports(ConfigAttribute) 方法在启动的时候被  
  AbstractSecurityInterceptor调用，来决定AccessDecisionManager  
  是否可以执行传递ConfigAttribute。   
  supports(Class)方法被安全拦截器实现调用，  
  包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型。  
    
* <p>Title: MyAccessDecisionManager</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年8月2日
 */

public class MyAccessDecisionManager implements AccessDecisionManager {

	private Logger logger = Logger.getLogger(AccessDecisionManager.class);
	
	/**
	 * 
	 */
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub

		if (configAttributes == null) {  
            return;  
        }  
		logger.info("The request url is "+object.toString());

		//所请求的资源拥有的权限(一个资源对多个权限) 
        Iterator<ConfigAttribute> ite = configAttributes.iterator();  
        
        while (ite.hasNext()) {  
            ConfigAttribute ca = ite.next();  
            //访问所请求资源所需要的权限    
            String needRole = ((SecurityConfig) ca).getAttribute(); 
            logger.info("The request url has the permission is "+needRole);
            //用户所拥有的权限authentication  
            for (GrantedAuthority ga : authentication.getAuthorities()) {
            	logger.info("The user has the permission is "+ga.getAuthority().trim());
                if(needRole.trim().equals(ga.getAuthority().trim()))
                {
                	logger.info("User has permission to access.");
                	return;
                }
            }
        }  
        logger.info("User has not permission to access.");
        throw new AccessDeniedException("no right!");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
