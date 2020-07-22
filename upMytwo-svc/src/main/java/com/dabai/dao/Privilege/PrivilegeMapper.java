package com.dabai.dao.Privilege;


import com.dabai.dto.Privilege.Privilege;

import java.util.List;

public interface PrivilegeMapper {
	public int insertPrivilege(Privilege privilege);
	public int updatePrivilege(Privilege privilege);
	public List<Privilege> findAllPrivilege();

}
