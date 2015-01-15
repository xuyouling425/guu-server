package cn.net.guu.core.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * 抽象的一个基本mapper
* <p>Title: BaseMapper</p>
* <p>Description: </p>
* <p>Company: www.guyuu.com</p>
* @author xurz
* @date 2014年6月7日
 */


public interface BaseMapper {

	public int countByExample(BaseExample example);

	public int insert(Object entity);

	public int insertSelective(Object entity);

	public int updateByPrimaryKey(Object entity);

	public int updateByPrimaryKeySelective(Object entity);

	public int updateByExample(@Param("entity") Object entity,
			@Param("example") BaseExample example);

	public int updateByExampleSelective(@Param("entity") Object entity,
			@Param("example") BaseExample example);

	public int deleteByPrimaryKey(String delId);

	public int deleteByExample(BaseExample example);

	public Object selectByPrimaryKey(String pkey);

	public List<?> selectByExample(BaseExample example);

}
