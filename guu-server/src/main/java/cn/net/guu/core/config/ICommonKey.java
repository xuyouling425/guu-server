package cn.net.guu.core.config;

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
public interface ICommonKey
{

	/**
	 * 成功标识
	 */
	public String SUCCESS = "SUCCESS";

	/**
	 * 失败
	 */
	public String FAILURE = "FAILURE";

	/**
	 * 等待
	 */
	public String WAITING = "WAITING";

	/**
	 * 错误标识
	 */
	public String ERROR = "ERROR";

	/**
	 * 默认标识
	 */
	public String DEFALUE = "DEFAULT";

	/**
	 * 可用
	 */
	public String ENABLED = "ENABLED";

	/**
	 * 可用
	 */
	public int ENABLED_INT = 1;

	/**
	 * 不可用
	 */
	public String DISABLE = "DISABLE";

	/**
	 * 不可用
	 */
	public int DISABLE_INT = 0;

	/**
	 * GUU
	 */
	public String GUU = "GUU";

	/**
	 * 日期转换格式 “YYYY-MM-DD”
	 */
	public String DATE_FORMAR_YYYY_MM_DD = "YYYY-MM-DD";

	/**
	 * 日期转换格式 “YYYY-MM-DD HH:mm:ss”
	 */
	public String DATE_FORMAR_YYYY_MM_DD_HH_mm_ss = "YYYY-DD-DD HH:mm:ss";

	/**
	 * 日期转换格式 “YYYYMMDDHHmmsss”
	 */
	public String DATE_FORMAR_YYYYMMDDHHmmsss = "YYYYDDDDHHmmsss";

	/**
	 * 用户sessionID
	 */
	public String SESSION_LOGIN_NAME = "SESSION_LOGIN_NAME";

}
