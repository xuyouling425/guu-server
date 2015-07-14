package cn.net.guu.system.mappers;

import java.sql.SQLException;
import java.util.List;

import cn.net.guu.core.mappers.BaseMapper;
import cn.net.guu.system.model.SysAuthorityResources;

public interface SysAuthorityResourcesMapper extends BaseMapper
{
	/**
	 * 批量添加权限和资源 对应关系
	* <p>Title: insertBatch</p>
	* @param authorityResources
	* @return
	* @throws SQLException
	 */
	public Integer insertBatch(List<SysAuthorityResources> authorityResources) throws SQLException; 
}