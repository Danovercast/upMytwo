package com.dabai.serviceImpl.Privilege;

import api.Privilege.RolePrivilegeService;
import com.dabai.dao.Privilege.RolePrivilegeMapper;
import com.dabai.dto.Privilege.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
* @author dabai: 

* 类说明  RolePrivilegeService实现类
*/
@DubboService
public class RolePrivilegeServiceImpl implements RolePrivilegeService {
	
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;

	@Override
	public Boolean addRolePrivilege(Integer role_id,Integer[]privilegeIds) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("role_id", role_id);
		map.put("create_time", new Date());
		map.put("privilegeIds", privilegeIds);
		return rolePrivilegeMapper.addRolePrivilege(map)==privilegeIds.length;
	}

	@Override
	public int deletePrivilegeForRole(Integer role_id,Integer[] privileges ) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("role_id", role_id);
		map.put("privilegeIds", privileges);
		return rolePrivilegeMapper.deletePrivilegeForRole(map);

	}

	@Override
	public List<Privilege> findPrivilegesByRoleId(Integer role_id) {
		return rolePrivilegeMapper.findPrivilegesByRoleId(role_id);
	}

}
