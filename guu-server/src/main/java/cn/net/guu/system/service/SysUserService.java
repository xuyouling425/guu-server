package cn.net.guu.system.service;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysUser;

/**
 * 用户接口,提供用户相关的接口。
 * 继承了baseService接口
* <p>Title: SysUserService</p>
* <p>Description: </p>
* <p>Company: www.guyuu.com</p>
* @author xurz
* @date 2014年7月23日
 */
public interface SysUserService extends BaseService {

	/**
	 * 通过登录名获得一个用户信息
	* <p>Title: selectUserByLoginName</p>
	* <p>Description: </p>
	* @param loginName 登录名
	* @return
	* @throws SQLException
	 */
	public SysUser selectUserByLoginName(String loginName) throws SQLException;
	
	/**
	 * 用户登录
	* <p>Title: userLogin</p>
	* <p>Description: </p>
	* @param loginName 登录名
	* @param loginPassword 登录密码
	* @return
	* @throws SQLException
	 */
	public SysUser userLogin(String loginName,String loginPassword) throws SQLException;
	
	/**
	 * 通过用户类型查询用户
	* <p>Title: selectUserByType</p>
	* @param userType
	* @return
	* @throws SQLException
	 */
	public List<SysUser> selectUserByType(String userType) throws SQLException;
	
}
