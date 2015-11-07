package cn.net.guu.system.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.core.common.CommonKey;
import cn.net.guu.core.spring.SpringContextHolder;
import cn.net.guu.system.model.SysAuthority;
import cn.net.guu.system.model.SysAuthorityExample;
import cn.net.guu.system.model.SysAuthorityExample.Criteria;
import cn.net.guu.system.service.SysAuthorityService;

/**
 * 权限缓存
 * <p>
 * Title: AuthorityCache
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年7月22日
 */
public class AuthorityCache
{

	/**
	 * 日志
	 */
	private Log log = LogFactory.getLog(AuthorityCache.class);

	/**
	 * ResourceCache
	 */
	private static AuthorityCache instance;

	/**
	 * resourceCache
	 */
	private static List<SysAuthority> authorityCache = new ArrayList<SysAuthority>();

	public static List<SysAuthority> getAuthorityCache()
	{
		return authorityCache;
	}

	/**
	 * 私有构造函数
	 * <p>
	 * Title:
	 * </p>
	 */
	private AuthorityCache()
	{

	}

	/**
	 * 资源缓存使用单例
	 * <p>
	 * Title: getInstance
	 * </p>
	 * 
	 * @return
	 */
	public static synchronized AuthorityCache getInstance()
	{
		if (null == instance)
		{
			instance = new AuthorityCache();
		}
		return instance;
	}

	/**
	 * 初始所有资源信息
	 * <p>
	 * Title: init
	 * </p>
	 */
	public void init()
	{
		log.info("Start to init the authority Cache...");
		refreshAuthorityCache();
		log.info("End to init the authority Cache...");
	}

	/**
	 * 查询所有的权限信息
	 * <p>
	 * Title: refreshResourceCache
	 * </p>
	 */
	public void refreshAuthorityCache()
	{
		log.info("Start to refresh the authority cache...");
		// 先清除缓存，在添加
		authorityCache.clear();

		SysAuthorityService service = SpringContextHolder.getBean("sysAuthorityServiceImpl");
		SysAuthorityExample example = new SysAuthorityExample();
		Criteria criteria = example.createCriteria();
		criteria.andAuthorityStatusEqualTo(CommonKey.ENABLED_INT);
		try
		{
			List<SysAuthority> authorities = (List<SysAuthority>) service.selectByExample(example);
			authorityCache = authorities;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Init the ResourceCache failed.", e);
		}

		log.info("End to refresh the ResourceCache...");
	}

}
