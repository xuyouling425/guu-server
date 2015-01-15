package cn.net.guu.core.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


import cn.net.guu.core.config.CommonKey;

/**
 * 基础工具类
 * 处理和字符串有关的
 * <p>Title: StringUtils</p>
 * <p>Description: </p>
 * <p>Company: www.guu.net.cn</p>
 * @author xurz
 * @date 2014年7月24日
 */
public class StringTools {

	/**
	 * 判断字符串是否为数字。true为数字
	 * 
	 * @Authod xurz
	 * @param str
	 * @return
	 * @return
	 */

	public static boolean isNumeric(String str) {
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		if (pattern.matcher(str).matches()) {
			Long num = Long.parseLong(str);
			if (num < 2147483647) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串不为null、空
	 * 
	 * @Authod xurz
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str) {
		if (str == null ){
			return false;
		}
		if(str.length()==0){
			return false;
		}
		if("".equals(str)){
			return false;
		}
		if("null".equals(str)){
			return false;
		}
		return true;
	}

	/**
	 * 集合不为null、空
	 * 
	 * @Authod xurz
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(List<?> list) {
		if (list == null) {
			return false;
		}
		if(list.size()==0){
			return false;
		}
		return true;
	}

	/**
	 * 字符串为null、空
	 * 
	 * @Authod xurz
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str) || str.isEmpty()) {
			return true;
		}
		return false;
	}


	/**
	 * HTML转换成字符格式
	 * @Authod xurz
	 * @param inputString
	 * @return
	 */
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
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
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	/**
	 * 去除HTML textarea 换行
	 * @Authod xurz
	 * @param textarea 内容
	 * @return
	 */
	public static String spaceTextArea(String textarea) {
		if(textarea ==null)
			return "";
		StringBuffer out = new StringBuffer();
		int length = textarea.length();
		for (int i = 0; i < length; i++) {
			char c = textarea.charAt(i);
			if(!Character.isWhitespace(c))
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
	public static String toChineseNumberCase(int n) {
		String chineseNumber = "";
		switch (n) {
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
	 * 获得唯一主键
	 * 主键有前缀+当前时间+随机数
	* <p>Title: getPrimaryKey</p>
	* <p>Description: </p>
	* @return
	 */
	public static String getPrimaryKey() {		
		SimpleDateFormat sdf = new SimpleDateFormat(CommonKey.DATE_FORMAR_YYYYMMDDHHmmsss);
		//日期
		String current = sdf.format(new Date());
		//随机数
		double random = Math.random()*1000+1000;
		return CommonKey.PREFIX+current+random;
	}

}
