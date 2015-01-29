package cn.net.guu.core.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 继承FreeMarkerView ，设置全局变量 base，方便ftl中使用
* <p>Title: MyFreeMarkerView</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年1月30日
 */
public class MyFreeMarkerView extends FreeMarkerView {

	private static final String CONTENT_PATH = "base";

	@Override
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		model.put(CONTENT_PATH, request.getContextPath());
		super.exposeHelpers(model, request);
	}
	
}
