package cn.net.guu.cms.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.net.guu.core.config.CommonKey;

public class Message
{
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.pid
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String pid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.type
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private Integer type;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.status
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private Integer status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.title
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String title;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.image
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String image;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.description
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String description;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.user_id
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.user_name
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String userName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.user_phone
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String userPhone;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.user_email
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String userEmail;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.creat_time
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	
	@DateTimeFormat(pattern = CommonKey.DATE_FORMAR_YYYY_MM_DD_HH_mm_ss)
	private Date creatTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.sequence
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private Integer sequence;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column guu_cms_message.content
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	private String content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column guu_cms_message.pid
	 *
	 * @return the value of guu_cms_message.pid
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getPid()
	{
		return pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.pid
	 *
	 * @param pid
	 *            the value for guu_cms_message.pid
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setPid(String pid)
	{
		this.pid = pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column guu_cms_message.type
	 *
	 * @return the value of guu_cms_message.type
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public Integer getType()
	{
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.type
	 *
	 * @param type
	 *            the value for guu_cms_message.type
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setType(Integer type)
	{
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column guu_cms_message.status
	 *
	 * @return the value of guu_cms_message.status
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public Integer getStatus()
	{
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.status
	 *
	 * @param status
	 *            the value for guu_cms_message.status
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column guu_cms_message.title
	 *
	 * @return the value of guu_cms_message.title
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.title
	 *
	 * @param title
	 *            the value for guu_cms_message.title
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column guu_cms_message.image
	 *
	 * @return the value of guu_cms_message.image
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.image
	 *
	 * @param image
	 *            the value for guu_cms_message.image
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setImage(String image)
	{
		this.image = image;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.description
	 *
	 * @return the value of guu_cms_message.description
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column
	 * guu_cms_message.description
	 *
	 * @param description
	 *            the value for guu_cms_message.description
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.user_id
	 *
	 * @return the value of guu_cms_message.user_id
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.user_id
	 *
	 * @param userId
	 *            the value for guu_cms_message.user_id
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.user_name
	 *
	 * @return the value of guu_cms_message.user_name
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.user_name
	 *
	 * @param userName
	 *            the value for guu_cms_message.user_name
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.user_phone
	 *
	 * @return the value of guu_cms_message.user_phone
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getUserPhone()
	{
		return userPhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column
	 * guu_cms_message.user_phone
	 *
	 * @param userPhone
	 *            the value for guu_cms_message.user_phone
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.user_email
	 *
	 * @return the value of guu_cms_message.user_email
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getUserEmail()
	{
		return userEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column
	 * guu_cms_message.user_email
	 *
	 * @param userEmail
	 *            the value for guu_cms_message.user_email
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.creat_time
	 *
	 * @return the value of guu_cms_message.creat_time
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public Date getCreatTime()
	{
		return creatTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column
	 * guu_cms_message.creat_time
	 *
	 * @param creatTime
	 *            the value for guu_cms_message.creat_time
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setCreatTime(Date creatTime)
	{
		this.creatTime = creatTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.sequence
	 *
	 * @return the value of guu_cms_message.sequence
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public Integer getSequence()
	{
		return sequence;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.sequence
	 *
	 * @param sequence
	 *            the value for guu_cms_message.sequence
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setSequence(Integer sequence)
	{
		this.sequence = sequence;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column
	 * guu_cms_message.content
	 *
	 * @return the value of guu_cms_message.content
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column guu_cms_message.content
	 *
	 * @param content
	 *            the value for guu_cms_message.content
	 *
	 * @mbggenerated Sun Mar 15 20:43:45 CST 2015
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
}