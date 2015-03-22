package cn.net.guu.cms.cache;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.cms.model.Business;
import cn.net.guu.cms.model.Message;
import cn.net.guu.cms.model.Project;
import cn.net.guu.cms.service.BusinessService;
import cn.net.guu.cms.service.MessageService;
import cn.net.guu.cms.service.ProjectService;
import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.spring.SpringContextHolder;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.service.SysUserService;

/**
 * 加载前台数据加载到缓存中
 *前台数据：团队，服务，新闻/消息，案例/项目
 * <p>
 * Title: WebCahce
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年3月21日
 */
public class WebCache
{

	/**
	 * 日志
	 */
	private Log log = LogFactory.getLog(WebCache.class);

	private static WebCache instance;

	/**
	 * 团队
	 */
	private static List<SysUser> teams;

	/**
	 * 服务
	 */
	private static List<Business> businesses;

	/**
	 * 新闻，消息
	 */
	private static List<Message> messages;

	/**
	 * 项目
	 */
	private static List<Project> projects;

	private WebCache()
	{
	}

	public static synchronized WebCache getInstance()
	{
		if (null == instance)
		{
			instance = new WebCache();
		}
		return instance;
	}

	/**
	 * 初始化，刷新
	* <p>Title: init</p>
	 */
	public void init()
	{
		refreshBusiness();
		refreshMessage();
		refreshProject();
		refreshTeam();
	}

	/**
	 * 刷新服务
	 * <p>
	 * Title: refreshTeams
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public void refreshBusiness()
	{
		log.info("Enter refresh business cache ...");
		BusinessService businessService = SpringContextHolder.getBean("businessServiceImpl");
		// 加载团队
		try
		{
			List<Business> busList = (List<Business>) businessService.selectByExample(null);
			businesses = busList;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("refresh business cache faile.", e);
		}

		log.info("Exist refresh business cache...");
	}

	/**
	 * 刷新消息
	 * <p>
	 * Title: refreshBusiness
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public void refreshMessage()
	{
		log.info("Enter refresh message cache...");
		MessageService messageService = SpringContextHolder.getBean("messageServiceImpl");
		// 加载团队
		try
		{
			List<Message> msgList = (List<Message>) messageService.selectByExample(null);
			messages = msgList;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("refresh message cache faile.", e);
		}
		log.info("Exist refresh message cache...");
	}

	/**
	 * 刷新案例
	 * <p>
	 * Title: refreshTeams
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public void refreshProject()
	{
		log.info("Enter refresh project cache...");
		ProjectService projectService = SpringContextHolder.getBean("projectServiceImpl");
		// 加载团队
		try
		{
			List<Project> projectList = (List<Project>) projectService.selectByExample(null);
			projects = projectList;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("refresh project cache faile.", e);
		}
		log.info("Exist refresh project cache...");
	}

	/**
	 * 刷新团队
	 * <p>
	 * Title: refreshTeams
	 * </p>
	 */
	public void refreshTeam()
	{
		log.info("Enter refresh team cache...");
		SysUserService teamService = SpringContextHolder.getBean("sysUserServiceImpl");
		// 加载团队
		try
		{
			List<SysUser> teamList = teamService.selectUserByType(CommonKey.USER_TYPE_TEAM);
			teams = teamList;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("refresh team cache faile.", e);
		}
		log.info("Exist refresh team cache...");
	}

	public List<SysUser> getTeams()
	{
		return teams;
	}

	public List<Business> getBusinesses()
	{
		return businesses;
	}

	public List<Message> getMessages()
	{
		return messages;
	}

	public List<Project> getProjects()
	{
		return projects;
	}

}
