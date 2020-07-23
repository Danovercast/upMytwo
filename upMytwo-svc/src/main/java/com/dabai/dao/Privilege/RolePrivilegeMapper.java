package com.dabai.dao.Privilege;


import com.dabai.dto.Privilege.Privilege;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RolePrivilegeMapper {
    public int addRolePrivilege(Map<String, Object> map);

    public int deletePrivilegeForRole(Map<String, Object> map);

    public List<Privilege> findPrivilegesByRoleId(Integer role_id);

}