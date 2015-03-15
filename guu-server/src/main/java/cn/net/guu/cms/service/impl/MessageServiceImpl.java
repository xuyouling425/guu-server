package cn.net.guu.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.cms.mappers.MessageMapper;
import cn.net.guu.cms.service.MessageService;
import cn.net.guu.core.service.impl.BaseServiceWithBlobImpl;

/**
 * 信息接口实现类
 * <p>
 * Title: MessageServiceImpl
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

@Service
public class MessageServiceImpl extends BaseServiceWithBlobImpl implements MessageService
{

	private MessageMapper messageMapper;

	public MessageMapper getMessageMapper()
	{
		return messageMapper;
	}

	@Resource
	public void setMessageMapper(MessageMapper messageMapper)
	{
		super.mapper = messageMapper;
		super.mapperPath = MessageMapper.class.getName();
		super.mapperWithBlob = messageMapper;
		this.messageMapper = messageMapper;
	}

}
