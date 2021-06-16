package com.dabai.dao.Privilege;


import com.dabai.dto.Privilege.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface RoleMapper {
    public Long insertRole(Role role);

    public int updateRole(Role role);

    public int deleteRole(Integer role_id);

    public List<Role> findActiveRoleList();

    public List<Role> findAllRoleList();

    public Role findRoleById(Integer role_id);

}