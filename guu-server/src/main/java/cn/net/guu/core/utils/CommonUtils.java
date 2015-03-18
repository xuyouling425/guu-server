package cn.net.guu.core.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.core.config.CommonKey;

/**
 * 基础工具类 处理和字符串有关的
 * <p>
 * Title: StringUtils
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2014年7月24日
 */
public class CommonUtils
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(CommonUtils.class);

	/**
	 * 判定结果集为空
	 * <p>
	 * Title: isEmpty
	 * </p>
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection)
	{
		return null == collection || collection.size() == 0;
	}

	/**
	 * 判定string数组为空
	 * <p>
	 * Title: isEmpty
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object[] str)
	{
		return null == str || str.length == 0;
	}

	/**
	 * HTML转换成字符格式
	 * 
	 * @Authod xurz
	 * @param inputString
	 * @return
	 */
	public static String html2Text(String inputString)
	{
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try
		{
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);

			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);

			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);

			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e)
		{
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	/**
	 * 去除HTML textarea 换行
	 * 
	 * @Authod xurz
	 * @param textarea
	 *            内容
	 * @return
	 */
	public static String spaceTextArea(String textarea)
	{
		if (textarea == null)
			return "";
		StringBuffer out = new StringBuffer();
		int length = textarea.length();
		for (int i = 0; i < length; i++)
		{
			char c = textarea.charAt(i);
			if (!Character.isWhitespace(c))
				out.append(c);
		}
		return out.toString();
	}

	/**
	 * 把阿拉伯数字翻译成中文大写数字
	 * 
	 * @Authod xurz
	 * @param n
	 * @return
	 */
	public static String toChineseNumberCase(int n)
	{
		String chineseNumber = "";
		switch (n)
		{
		case 0:
			chineseNumber = "零";
			break;
		case 1:
			chineseNumber = "壹";
			break;
		case 2:
			chineseNumber = "貳";
			break;
		case 3:
			chineseNumber = "叁";
			break;
		case 4:
			chineseNumber = "肆";
			break;
		case 5:
			chineseNumber = "伍";
			break;
		case 6:
			chineseNumber = "陆";
			break;
		case 7:
			chineseNumber = "柒";
			break;
		case 8:
			chineseNumber = "捌";
			break;
		case 9:
			chineseNumber = "玖";
			break;
		default:
			break;
		}
		return chineseNumber;
	}

	/**
	 * 获得唯一主键 若前缀不为空，则主键：前缀+系统时间+3位随机数 前缀为空或前缀长度超过10位：系统时间+3位随机数
	 * <p>
	 * Title: getPrimaryKey
	 * </p>
	 * 
	 * @param prefix
	 *            前缀，前缀长度不能大于10
	 * @return
	 */
	public static String getPrimaryKey(String prefix)
	{
		String priKey = System.currentTimeMillis() + "" + (int) (Math.random() * 1000);
		if (StringUtils.isEmpty(prefix) || prefix.length() > 10)
		{
			priKey = prefix + priKey;
		}
		log.info("Generated primary key is [" + priKey + "]");
		return priKey;
	}

	/**
	 * 获得唯一主键 主键：系统时间+3为随机数
	 * <p>
	 * Title: getPrimaryKey
	 * </p>
	 * 
	 * @return
	 */
	public static String getPrimaryKey()
	{
		String pk = System.currentTimeMillis() + "" + (int) (Math.random() * 1000);
		log.info("Generated primary key is [" + pk + "]");
		return pk;
	}
	
	/**
	 * 返回当前时间 
	 * 格式：yyyyMMddHHmmsss
	* <p>Title: getDateTime</p>
	* @return
	 */
	public static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat(CommonKey.DATE_FORMAR_YYYYMMDDHHmmsss);
		return sdf.format(new Date());
	}

	/**
	 * 将数组转换list集合，
	 * <p>
	 * Title: changeList
	 * </p>
	 * 
	 * @param str
	 * @return 传入集合为空，返回null,集合里面的空也会过滤掉
	 */
	public static List<String> changeList(String[] str)
	{
		if (isEmpty(str))
		{
			return null;
		} else
		{
			List<String> tempList = new ArrayList<String>();
			for (String temp : str)
			{
				if (!StringUtils.isEmpty(temp))
				{
					tempList.add(temp);
				}
			}

			return tempList;
		}
	}

}
