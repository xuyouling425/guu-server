package cn.net.guu.core.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.net.guu.core.common.CommonKey;

/**
 * 加密工具类
 * <p>
 * Title: EncryptUtils
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2014年8月5日
 */
public class EncryptUtils
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(EncryptUtils.class);

	/**
	 * 对字符串进行MD5进行加密处理
	 * 
	 * @param msg
	 *            待加密的字符串
	 * @return 加密后字符串
	 */
	private  String encryptMD5(String msg)
	{
		return encrypt(msg, null);
	}

	/**
	 * 基本MD5加密处理
	 * 
	 * @param msg
	 * @param typt
	 * @return
	 */
	private static String encrypt(String msg, String type)
	{
		log.info("Entering encrypt().");
		MessageDigest md;
		StringBuilder password = new StringBuilder();

		try
		{
			md = MessageDigest.getInstance("MD5");

			if (StringUtils.isEmpty(type))
			{
				md.update(msg.getBytes());

			} else
			{
				md.update((msg + type).getBytes());
			}

			byte[] bytes = md.digest();
			for (int i = 0; i < bytes.length; i++)
			{
				String param = Integer.toString((bytes[i] & 0xff) + 0x100, 16);
				password.append(param.substring(1));
			}
		} catch (Exception e)
		{
			log.error("Encrypt faild.", e);
		}
		log.info("Exiting encrypt().");
		return password.toString().toUpperCase();
	}

	/**
	 * 盐值的原理非常简单，就是先把密码和盐值指定的内容合并在一起，再使用md5对合并后的内容进行演算，
	 *  这样一来，就算密码是一个很常见的字符串，再加上用户名，最后算出来的md5值就没那么容易猜出来了。
	 * 因为攻击者不知道盐值的值，也很难反算出密码原文。
	 * 
	 * @param msg
	 * @return
	 */
	public static String encryptSalt(String msg, String salt)
	{
		//去掉前缀
		salt = salt.substring(CommonKey.GUU.length());
		return encrypt(msg, salt);
	}

	/**
	 * SHA（Secure Hash Algorithm，安全散列算法）是消息摘要算法的一种，被广泛认可的MD5算法的继任者。
	 *  SHA算法家族目前共有SHA-0、SHA-1、SHA-224、SHA-256、SHA-384和SHA-512五种算法，
	 * 通常将后四种算法并称为SHA-2算法
	 * 
	 * @param msg
	 * @return
	 */
	private  String encryptSHA(String msg)
	{
		log.info("Entering encryptSHA().");
		String salt = getSaltSHA1();
		StringBuilder sb = new StringBuilder();
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(msg.getBytes());
			for (int i = 0; i < bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (Exception e)
		{
			log.error("EncryptSHA faild.", e);
		}
		log.info("Exiting encryptSHA().");
		return sb.toString().toUpperCase();
	}

	/**
	 * PBKDF2加密
	 * 
	 * @param msg
	 * @return
	 */
	private  String encryptPBKDF2(String msg, String salts)
	{
		log.info("Entering encryptPBKDF2().");
		String pbkdf2 = "";
		try
		{
			int iterations = 1000;
			char[] chars = msg.toCharArray();
			byte[] salt = getSalt(salts).getBytes();

			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();

			pbkdf2 = iterations + toHex(salt) + toHex(hash);
		} catch (Exception e)
		{
			log.error("EncryptPBKDF2() faild.", e);
		}
		log.info("Exiting encryptPBKDF2().");
		return pbkdf2;
	}

	/**
	 * 转化十六进制
	 * 
	 * @param array
	 * @return
	 */
	private static String toHex(byte[] array)
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
		{
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else
		{
			return hex;
		}
	}

	/**
	 * [list] [*]SHA-1 (Simplest one – 160 bits Hash)
	 * 
	 * [*]SHA-256 (Stronger than SHA-1 – 256 bits Hash)
	 * 
	 * [*]HA-384 (Stronger than SHA-256 – 384 bits Hash)
	 * 
	 * [*]SHA-512 (Stronger than SHA-384 – 512 bits Hash)
	 * 
	 * [/list]
	 * 
	 * @return
	 */
	private static String getSaltSHA1()
	{
		log.info("Entering getSaltSHA1().");
		SecureRandom sr;
		byte[] salt = new byte[16];
		try
		{
			sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e)
		{
			log.info("Get saltSHA1 failed.", e);
		}
		log.info("Exiting getSaltSHA1().");
		return salt.toString();
	}

	/**
	 * 盐值的原理非常简单，就是先把密码和盐值指定的内容合并在一起，再使用md5对合并后的内容进行演算， 这样一来，就算密码是一个很常见的字符串，再加上用户名，最后算出来的md5值就没那么容易猜出来了。 因为攻击者不知道盐值的值，也很难反算出密码原文。
	 * 
	 * @return
	 */
	private static String getSalt(String salt)
	{
		log.info("Entering getSaltSHA().");
		SecureRandom sr;
		// byte[] salt = new byte[16];
		try
		{
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
			sr.nextBytes(salt.getBytes());
		} catch (Exception e)
		{
			log.error("Get saltSHA faild.", e);
		}
		log.info("Exiting getSaltSHA().");
		return salt.toString();
	}

	
}
