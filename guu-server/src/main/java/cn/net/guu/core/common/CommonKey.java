package cn.net.guu.core.common;

/**
 * 配置一些共用的常量key
 * <p>
 * Title: CommonKey
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
public interface CommonKey
{

	/**
	 * SERIALVERSIONUID
	 */
	long SERIALVERSIONUID = 1L;

	/**
	 * GUU
	 */
	String GUU = "guu";

	/**
	 * 成功标识
	 */
	String SUCCESS = "SUCCESS";

	/**
	 * 失败
	 */
	String FAILURE = "FAILURE";

	/**
	 * 等待
	 */
	String WAITING = "WAITING";

	/**
	 * 错误标识
	 */
	String ERROR = "ERROR";

	/**
	 * 默认标识
	 */
	String DEFALUE = "DEFAULT";

	/**
	 * 可用
	 */
	String ENABLED = "ENABLED";

	/**
	 * 可用
	 */
	int ENABLED_INT = 1;

	/**
	 * 不可用
	 */
	String DISABLE = "DISABLE";

	/**
	 * 不可用
	 */
	int DISABLE_INT = 0;
	


	/**
	 * 日期转换格式 “YYYY-MM-DD”
	 */
	String DATE_FORMAR_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 日期转换格式 “YYYY-MM-DD HH:mm:ss”
	 */
	String DATE_FORMAR_YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期转换格式 “YYYYMMDDHHmmsss”
	 */
	String DATE_FORMAR_YYYYMMDDHHmmsss = "yyyyMMddHHmmsss";

	/**
	 * 用户sessionID
	 */
	String SESSION_LOGIN_NAME = "SESSION_LOGIN_NAME";

	/**
	 * 图片上传路径
	 */
	String UPLOAD_IMAGE_PATH = "resources/uploads/images/";
	
	/**
	 * 图片上传路径
	 */
	String UPLOAD_TEMPLATE_COVER_PATH = "resources/uploads/cover/";
	
	/**
	 * 模板路径
	 */
	String TEMPLATES_PATH = "webtemplate/";
	
	/**
	 * 模板刚创建，还未审批
	 */
	int TEMPLATE_APPROVAL_CREATE =0;
	
	/**
	 * 模板正在审批
	 */
	int TEMPLATE_APPROVAL_DOING=1;
	
	/**
	 * 模板审批通过
	 */
	int TEMPLATE_APPROVAL_OK =2;
	
	/**
	 * 模板审批不通过
	 */
	int TEMPLATE_APPROVAL_NOTOK =3;
	
	/**
	 * 管理员上传路径
	 */
	String UPLOAD_ADMIN_FILE_PATH = "resources/uploads/admin/";

	/**
	 * 默认文件上传路径
	 */
	String UPLOAD_DEFAULT_PATH = "resources/uploads/default/";

	/**
	 * 系统异常跳转的错误页面路径
	 */
	String ADMIN_ERROR_URL = "admin/error";

	/**
	 * 首页
	 */
	String INDEX = "index";

	/**
	 * 中文正则
	 */
	String CHINESE_PATTEN = "[\\u4e00-\\u9fa5]";

	/**
	 * 用户类型：TEAM（团队）
	 */
	String USER_TYPE_TEAM = "TEAM";
	
	/**
	 * 用户类型：ADMIN（管理员）
	 */
	String USER_TYPE_ADMIN = "ADMIN";
	
	/**
	 * 前端缓存
	 */
	String WEB_CACHE ="webCache";
	
	/**
	 * 用户初始默认密码 Admin_123
	 */
	String USER_DEFAULT_PASSWORD = "Admin_123";
	
	/**
	 * 资源缓存
	 */
	String RESOURCE_CACHE ="resourceCache";
	
	/**
	 * 权限缓存
	 */
	String AUTHORITY_CACHE ="authorityCache";

	/**
	 * 消息类型：留言
	 */
	int MESSAGE_TYPE_LEAVE_MSG = 3;

}
