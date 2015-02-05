package cn.net.guu.core.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 获取spring bean 上下文工具
* <p>Title: SpringContextHolder</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */
@Service
public class SpringContextHolder implements ApplicationContextAware {
	

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(SpringContextHolder.class);
	
	/**
	 * applicationContext 上下文
	 */
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		applicationContext = ac;
		log.info("Srping applicationContext init successfully.");
	}

	/**
	 * 得到spring上下文对象
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 得到spring注入的bean实例
	 * 
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}
}
