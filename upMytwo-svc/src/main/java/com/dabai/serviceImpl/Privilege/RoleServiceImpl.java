package com.dabai.serviceImpl.Privilege;

import api.Privilege.RoleService;
import com.dabai.dao.Privilege.RoleMapper;
import com.dabai.dto.Privilege.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  RoleService实现类
 */
@DubboService
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer addtRole(Role role) {
        role.setCreate_time(new Date());
        role.setIsactive(role.isIsactive() ? true : false);
        roleMapper.insertRole(role);
        return role.getRole_id();
    }

    @Override
    public Boolean updateRole(Role role) {
        if (null == role.getClass())
            role.setCreate_time(new Date());
        role.setLast_edit(new Date());
        return roleMapper.updateRole(role) == 1;

    }

    @Override
    public Boolean deleteRole(Integer role_id) {
        return roleMapper.deleteRole(role_id) == 1;

    }

    @Override
    public List<Role> findActiveRoleList() {

        return roleMapper.findActiveRoleList();
    }

    @Override
    public List<Role> findAllRoleList() {
        return roleMapper.findAllRoleList();
    }

    @Override
    public Role findRoleById(Integer role_id) {

        return roleMapper.findRoleById(role_id);
    }

}
