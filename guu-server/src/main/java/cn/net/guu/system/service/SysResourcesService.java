package cn.net.guu.system.service;



import java.sql.SQLException;
import java.util.List;


import cn.net.guu.core.service.BaseService;
import cn.net.guu.system.model.SysResources;

/**
 * 资源接口，
 * 继承 基础接口
* <p>Title: SysResourcesService</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */
public interface SysResourcesService extends BaseService {
	
	/**
	 * 通过用户登录名 获得当前用户下所有的资源信息
	* <p>Title: selectResourcesByLoginName</p>
	* <p>Description: </p>
	* @param loginName 用户登录名
	* @return
	* @throws SQLException
	 */
	public List<SysResources> selectResourcesByLoginName(String loginName) throws SQLException;
	
	/**
	 * 通过权限的code查询对应的资源列表
	* <p>Title: selectResourcesByLoginName</p>
	* <p>Description: </p>
	* @param authCode 权限code
	* @return
	* @throws SQLException
	 */
	public List<SysResources> selectResourcesByAuthId(String authCode) throws SQLException;
	 

}
