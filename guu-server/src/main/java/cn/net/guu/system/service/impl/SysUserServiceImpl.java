package cn.net.guu.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.net.guu.core.config.CommonKey;
import cn.net.guu.core.service.impl.BaseServiceImpl;
import cn.net.guu.core.utils.CommonUtils;
import cn.net.guu.core.utils.EncryptUtils;
import cn.net.guu.system.mappers.SysUserMapper;
import cn.net.guu.system.model.SysUser;
import cn.net.guu.system.model.SysUserExample;
import cn.net.guu.system.model.SysUserExample.Criteria;
import cn.net.guu.system.service.SysUserService;

/**
 * 用户接口实现类
* <p>Title: SysUserServiceImpl</p>
* <p>Description: </p>
* <p>Company: www.guyuu.com</p>
* @author xurz
* @date 2014年7月23日
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl implements
		SysUserService {
	
	/**
	 * 用户mapper
	 */
	private SysUserMapper sysUserMapper;

	@Resource
	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		super.mapper = sysUserMapper;
		super.mapperPath = SysUserMapper.class.getName();
		this.sysUserMapper = sysUserMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SysUser selectUserByLoginName(String loginName) throws SQLException {
		// TODO Auto-generated method stub
		
		SysUser user = null;
		//设置用户登录名的查询条件
		SysUserExample userExample = new SysUserExample();
		Criteria userCriteria = userExample.createCriteria();
		userCriteria.andLoginNameEqualTo(loginName);
		List<SysUser> users = (List<SysUser>) sysUserMapper.selectByExample(userExample);
		if(!CommonUtils.isEmpty(users)){
			user =users.get(0);
		}		
		return user;
	}

	@Override
	public SysUser userLogin(String loginName, String loginPassword)
			throws SQLException {
		// TODO Auto-generated method stub
		//先通过登录名获得用户
		SysUser user = selectUserByLoginName(loginName);
		if(user!=null){
			//用户存在，则验证用户密码
			//获得加密salt
			String salt = user.getUserId().substring(CommonKey.GUU.length());
			String saltPwd = EncryptUtils.encryptSalt(loginPassword, salt);
			//匹配密码				
			user = (saltPwd.equals(user.getLoginPassword())) ? user:null;			
		}
		return user;
	}
	

	
}
