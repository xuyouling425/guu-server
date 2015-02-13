package cn.net.guu.core.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.net.guu.core.mappers.BaseExample;
import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.core.service.BaseService;

/**
 * 实现BaseService基础 的方法，通用的  新增，修改，查询，删除的实现
 * <p>
 * Title: BaseServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guyuu.com
 * </p>
 * 
 * @author xurz
 * @date 2014年6月7日
 */
public class BaseServiceImpl extends SqlSessionDaoSupport implements BaseService
{

	/**
	 * mapper接口
	 */
	protected BaseMapper mapper;

	/**
	 * mapper接口类全名称
	 */
	protected String mapperPath = null;

	@Resource
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
	{
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public Integer add(Object entity) throws SQLException
	{
		return mapper.insert(entity);
	}

	public Integer addSelective(Object entity) throws SQLException
	{
		return mapper.insertSelective(entity);
	}

	public Integer updateBypk(Object entity) throws SQLException
	{
		return mapper.updateByPrimaryKey(entity);
	}

	public Integer updateBypkSelective(Object entity) throws SQLException
	{
		return mapper.updateByPrimaryKeySelective(entity);
	}

	public Integer updateByExample(Object entity, BaseExample example) throws SQLException
	{
		return mapper.updateByExample(entity, example);
	}

	public Integer updateByExampleSelective(Object entity, BaseExample example) throws SQLException
	{
		return mapper.updateByExampleSelective(entity, example);
	}

	public Integer deleteBypk(String pkey) throws SQLException
	{
		return mapper.deleteByPrimaryKey(pkey);
	}

	public Integer deleteByExample(BaseExample example) throws SQLException
	{
		return mapper.deleteByExample(example);
	}

	public Object selectBypk(String pkey) throws SQLException
	{
		return mapper.selectByPrimaryKey(pkey);
	}

	public List<?> selectByExample(BaseExample example) throws SQLException
	{
		return mapper.selectByExample(example);
	}

	public List<?> selectPageByExample(BaseExample example, int start, int limit) throws SQLException
	{
		List<?> list = null;
		if (this.mapperPath == null)
		{
			return list;
		}
		list = getSqlSession().selectList(this.mapperPath + ".selectByExample", example, new RowBounds(start, limit));
		return list;
	}

	public Integer selectCount(BaseExample example) throws SQLException
	{
		return mapper.countByExample(example);
	}

}
