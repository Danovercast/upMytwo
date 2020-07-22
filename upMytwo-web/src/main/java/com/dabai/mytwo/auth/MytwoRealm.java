package com.dabai.mytwo.auth;

import com.dabai.dto.Privilege.Privilege;
import api.Privilege.RolePrivilegeService;
import api.User.UserService;
import com.dabai.mytwo.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**  
* @author dabai: 

* 类说明  实现realm功能
*/
public class MytwoRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String jwt=(String) arg0.getPrimaryPrincipal();
		System.out.println("查询了authorization");
		if(JwtUtil.verifyToken(jwt)){
			Integer roleId = (Integer) JwtUtil.parseToken(jwt).get("roleid");
			if(roleId!=null){
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				List<Privilege> list = rolePrivilegeService.findPrivilegesByRoleId(roleId);
				for (Privilege privilege : list) {
					info.addStringPermission(privilege.getPrivilege_name());
					System.out.println("查询了authorization"+privilege.getPrivilege_name());
				}
				return info;
			}
		}
		return null;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) arg0;
		String jwt=(String) passwordToken.getPrincipal();
		if(JwtUtil.verifyToken(jwt)){
			return new SimpleAuthenticationInfo(jwt, jwt, this.getName());
		}
		return null;
	}
	public void setName(String name) {
		this.setName(name);
	}
	public String getName() {
		return "mytwoRealm";
	}
}
