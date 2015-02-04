package cn.net.guu.core.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * spring mvc 拦截器
 * <p>
 * Title: SystemHandlerInterceptorAdapter
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2014年7月29日
 */
public class SystemHandlerInterceptorAdapter extends HandlerInterceptorAdapter
{

	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(SystemHandlerInterceptorAdapter.class);

	/**
	 * 预处理回调方法： 在Controller方法前进行拦截 如果返回false 从当前拦截器往回执行所有拦截器的afterCompletion方法,再退出拦截器链. 如果返回true 执行下一个拦截器,直到所有拦截器都执行完毕.
	 * 再运行被拦截的Controller. 然后进入拦截器链,从最后一个拦截器往回运行所有拦截器的postHandle方法. 接着依旧是从最后一个拦截器往回执行所有拦截器的afterCompletion方法.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 请求地址
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();

		log.info("##########Entering preHandle().");
		log.info("##########Request uri=[" + uri + "] \n url=[" + url + "]");
		// HttpSession session = request.getSession(false); //获取session，没有返回null
		/*
		 * //当不是资源文件夹 if(uri.indexOf("/resources/")<0){
		 * 
		 * Set<String> keySet = CommonKey.getResourcesMap().keySet(); for (Iterator iterator = keySet.iterator();
		 * iterator.hasNext();) { String keyString = (String) iterator.next();
		 * System.out.println("==========加载的资源信息key========="+keyString+"============"); }
		 * 
		 * 
		 * 
		 * }
		 */
		// if(session == null){
		// //session为空时，校验此uri是否在资源表中存在，如果存在，把请求转向登录页面
		// return this.checkResourceUrl(request, response, uri);
		// }
		// 获取当前登录者的session信息
		// UserSessionBean userSession = (UserSessionBean)session.getAttribute(ICommKey.KH_USER_SESSION_BEAN);
		// 如果不存在登录者session，则返回登录页面
		// if(userSession == null){
		// //登录者session为空时，校验此uri是否在资源表中存在，如果存在，把请求转向登录页面
		// return this.checkResourceUrl(request, response, uri);
		// }
		// 直接转向页面
		// request.getRequestDispatcher("/index.jsp").forward(request, response);

		log.info("##########Exiting preHandle().");
		return super.preHandle(request, response, handler);
	}

	/**
	 * 后处理回调方法: 实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象） 对模型数据进行处理或对视图进行处理,modelAndView也可能为null。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 整个请求处理完毕回调方法: 即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间， 还可以进行一些资源清理，类似于try-catch-finally中的finally，
	 * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		super.afterCompletion(request, response, handler, ex);
	}

}
