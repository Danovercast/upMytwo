package com.dabai.mytwo.auth;

import com.dabai.dto.Privilege.Privilege;
import api.Privilege.RolePrivilegeService;
import api.User.UserService;
import com.dabai.mytwo.util.JwtUtil;
import com.dabai.mytwo.util.Md5Utils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author dabai:
 * <p>
 * 类说明  实现realm功能
 */
public class MytwoRealm extends AuthorizingRealm {
    @DubboReference
    private UserService userService;
    @DubboReference
    private RolePrivilegeService rolePrivilegeService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        String jwt = (String) arg0.getPrimaryPrincipal();
        System.out.println("查询了authorization");
        if (JwtUtil.verifyToken(jwt)) {
            Integer roleId = (Integer) Objects.requireNonNull(JwtUtil.parseToken(jwt)).get("roleid");
            if (roleId != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                List<Privilege> list = rolePrivilegeService.findPrivilegesByRoleId(roleId);
                for (Privilege privilege : list) {
                    info.addStringPermission(privilege.getPrivilege_name());
                    System.out.println("查询了authorization" + privilege.getPrivilege_name());
                }
                return info;
            }
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        return new SimpleAuthenticationInfo(username, Md5Utils.toMd5(password,username,2), ByteSource.Util.bytes(username), this.getName());
    }

    public void setName(String name) {
        this.setName(name);
    }

    public String getName() {
        return "mytwoRealm";
    }
}
