package cn.net.guu.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.system.mappers.SysResourcesMapper;
import cn.net.guu.system.model.SysResources;
import cn.net.guu.system.service.SysResourcesService;

/**
 * 资源接口实现类
* <p>Title: SysResourcesServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2014年7月29日
 */
@Service
public class SysResourcesServiceImpl extends BaseServiceImpl implements
		SysResourcesService {

	/**
	 * 资源mapper
	 */
	private SysResourcesMapper sysResourcesMapper;
	
	
	public SysResourcesMapper getSysResourcesMapper() {
		return sysResourcesMapper;
	}

	@Resource
	public void setSysResourcesMapper(SysResourcesMapper sysResourcesMapper) {
		super.mapper = sysResourcesMapper;
		super.mapperPath = SysResourcesMapper.class.getName();
		this.sysResourcesMapper = sysResourcesMapper;
	}


	@Override
	public List<SysResources> selectResourcesByLoginName(String loginName)
			throws SQLException {
		// TODO Auto-generated method stub
		return sysResourcesMapper.selectResourcesByLoginName(loginName);
	}
	
	@Override
	public List<SysResources> selectResourcesByAuthCode(String authCode){
		return  sysResourcesMapper.selectResourcesByAuthCode(authCode);
	}

}
