package com.dabai.dao.Privilege;


import com.dabai.dto.Privilege.Privilege;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrivilegeMapper {
    public int insertPrivilege(Privilege privilege);

    public int updatePrivilege(Privilege privilege);

    public List<Privilege> findAllPrivilege();

}
