package api.Privilege;

import com.dabai.dto.Privilege.Privilege;

import java.util.List;

/**  
* @author dabai: 

* 类说明  RolePrivilegeService接口类
*/
public interface RolePrivilegeService {
	public Boolean addRolePrivilege(Integer role_id, Integer[] privilegeIds);
	public int deletePrivilegeForRole(Integer role_id, Integer[] privilegeIds);
	public List<Privilege>findPrivilegesByRoleId(Integer role_id);

}
