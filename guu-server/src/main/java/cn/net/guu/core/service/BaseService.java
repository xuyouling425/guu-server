package cn.net.guu.core.service;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.mappers.BaseExample;

/**
 * 抽象出通用增加，删除，查询，修改的接口方法
* <p>Title: BaseService</p>
* <p>Description: </p>
* <p>Company: www.guyuu.com</p>
* @author xurz
* @date 2014年6月7日
 */
public  interface BaseService {

	/**
	 * 新增一条数据
	 * 
	 * @Authod xurz
	 * @param entity 实体对象
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer add(Object entity) throws SQLException;

	/**
	 * 新增一条数据,过滤空字段
	 * 
	 * @Authod xurz
	 * @param entity 实体对象
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer addSelective(Object entity) throws SQLException;

	/**
	 * 修改一条数据，根据id修改
	 * 
	 * @Authod xurz
	 * @param entity 实体对象
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer updateBypk(Object entity) throws SQLException;

	/**
	 * 修改一条数据,根据id修改，过滤空字段
	 * 
	 * @Authod xurz
	 * @param entity 实体对象
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer updateBypkSelective(Object entity) throws SQLException;

	/**
	 * 修改数据，根据查询条件修改
	 * 
	 * @Authod xurz
	 * @param entity 实体对象
	 * @param example 查询条件
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer updateByExample(Object entity, BaseExample example) throws SQLException;

	/**
	 * 修改数据，根据查询条件修改，过滤空字段
	 * 
	 * @Authod xurz
	 * @param entity  实体对象
	 * @param example 查询对象
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer updateByExampleSelective(Object entity, BaseExample example) throws SQLException;

	/**
	 * 删除一条数据，根据id删除
	 * 
	 * @Authod xurz
	 * @param id  主键
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer deleteBypk(String pkey) throws SQLException;

	/**
	 * 删除数据，根据查询条件删除
	 * 
	 * @Authod xurz
	 * @param example 查询对象
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer deleteByExample(BaseExample example) throws SQLException;

	/**
	 * 获取一条数据，根据id获取
	 * 
	 * @Authod xurz
	 * @param id 主键
	 * @return
	 * @throws SQLException
	 */
	public abstract Object selectBypk(String pkey) throws SQLException;

	/**
	 * 获取数据，根据查询条件获取
	 * 
	 * @Authod xurz
	 * @return
	 * @throws SQLException
	 */
	public abstract List<?> selectByExample(BaseExample example) throws SQLException;

	/**
	 * 获取数据的总和
	 * 
	 * @Authod xurz
	 * @return
	 * @throws SQLException
	 */
	public abstract Integer selectCount(BaseExample example) throws SQLException;

	/**
	 * 分页查询
	 * 
	 * @Authod xurz
	 * @param example 查询条件
	 * @param start 开始记录数
	 * @param limit 每页显示记录数
	 * @return
	 * @throws SQLException
	 */
	public List<?> selectPageByExample(BaseExample example,int start, int limit) throws SQLException;
	
}
