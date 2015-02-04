package cn.net.guu.core.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 继承FreeMarkerView ，设置全局变量 base，方便ftl中使用
 * <p>
 * Title: MyFreeMarkerView
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年1月30日
 */
public class MyFreeMarkerView extends FreeMarkerView
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(MyFreeMarkerView.class);

	/**
	 * FreeMarkerView 设置${base}变量，变量值为项目根目录
	 */
	private static final String CONTENT_PATH = "base";

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception
	{
		// TODO Auto-generated method stub
		log.info("##########Entering exposeHelpers().");
		model.put(CONTENT_PATH, request.getContextPath());
		log.info("##########Set FreeMarkerView base=[" + request.getContextPath() + "] successfully");
		super.exposeHelpers(model, request);
		log.info("##########Exiting exposeHelpers().");
	}

}
