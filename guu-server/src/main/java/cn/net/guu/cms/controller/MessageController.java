package cn.net.guu.cms.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.net.guu.cms.cache.WebCache;
import cn.net.guu.cms.model.Message;
import cn.net.guu.cms.model.MessageExample;
import cn.net.guu.cms.model.MessageExample.Criteria;
import cn.net.guu.cms.service.MessageService;
import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.config.ICommonKey;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.UploadUtils;

/**
 * 信息 接口类
 * <p>
 * Title: MessageController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年3月15日
 */
@Controller
@RequestMapping("/message")
public class MessageController
{
	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(MessageController.class);

	@Resource(name = "messageServiceImpl")
	private MessageService messageService;

	/**
	 * 跳转到新增信息页面
	 * <p>
	 * Title: toAdd
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd()
	{
		return new ModelAndView("admin/addMessage");
	}

	@RequestMapping("/add")
	public ModelAndView addMessage(HttpServletRequest request, Message message)
	{

		log.info("Entering addMessage()..");
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);
		// 设置上传图片
		message.setImage(imgPath);
		// 设置主键
		message.setPid(CommonUtils.getPrimaryKey());
		message.setCreatTime(new Date());

		try
		{
			// 调用添加接口
			messageService.add(message);
			// 刷新缓存
			WebCache.getInstance().refreshMessage();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Add Message Failed.", e);
		}

		log.info("Exiting addMessage()..");
		return queryMessage();

	}

	/**
	 * 后台管理，查询出所有的信息
	 * <p>
	 * Title: queryMessage
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	public ModelAndView queryMessage()
	{
		ModelAndView maView = new ModelAndView("admin/message");
		try
		{
			MessageExample example = new MessageExample();
			example.setOrderByClause(" creat_time desc");

			List<Message> messages = (List<Message>) messageService.selectByExample(example);

			maView.addObject("messages", messages);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Query Message Faild.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}
		return maView;
	}

	/**
	 * 删除信息
	 * <p>
	 * Title: deleteMessage
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView deleteMessage(HttpServletRequest request)
	{
		// 获得前台传入的主键集合
		String pids = request.getParameter("pids");
		String[] pid = pids.split("-");
		List<String> pidList = CommonUtils.changeList(pid);
		// 传入值不为空
		if (!CommonUtils.isEmpty(pidList))
		{
			MessageExample example = new MessageExample();
			Criteria cri = example.createCriteria();
			// 设置删除条件
			cri.andPidIn(pidList);
			try
			{
				messageService.deleteByExample(example);
				// 刷新缓存
				WebCache.getInstance().refreshMessage();

			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				log.error("Delete message faild", e);
				return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
			}
		}
		return queryMessage();
	}

	/**
	 * 通过主键获得一个message信息，跳转到修改页面
	 * <p>
	 * Title: toUpdate
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request)
	{
		ModelAndView maView = new ModelAndView("admin/updateMessage");
		String pid = request.getParameter("pids");

		try
		{
			Message message = (Message) messageService.selectBypk(pid);
			maView.addObject("message", message);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Query message faile", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return maView;
	}

	/**
	 * 更新一条信息
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView updateMessage(HttpServletRequest request, Message message)
	{
		String imgPath = UploadUtils.uploadFile(request, CommonKey.UPLOAD_IMAGE_PATH);

		if (!StringUtils.isEmpty(imgPath))
		{
			message.setImage(imgPath);
		}

		try
		{
			messageService.updateBypkSelective(message);
			// 刷新缓存
			WebCache.getInstance().refreshMessage();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			log.error("Update message failed.", e);
			return new ModelAndView(CommonKey.ADMIN_ERROR_URL);
		}

		return queryMessage();
	}

	/**
	 * 首页留言
	 * <p>
	 * Title: leaveMsg
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/leaveMsg")
	@ResponseBody
	public String leaveMsg(HttpServletRequest request)
	{

		Message message = new Message();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String msg = request.getParameter("message");
		
		// 设置主键
		message.setPid(CommonUtils.getPrimaryKey());
		message.setCreatTime(new Date());
		message.setType(ICommonKey.MESSAGE_TYPE_LEAVE_MSG);
		
		message.setUserPhone(phone);
		message.setUserEmail(email);
		message.setContent(msg);
		message.setUserName(name);
		try
		{
			messageService.add(message);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			log.error("Leave message failed.", e);
			return CommonKey.ERROR;
		}
		return CommonKey.SUCCESS;
	}

}
