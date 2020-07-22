package api.Privilege;

import com.dabai.dto.Privilege.Role;

import java.util.List;

/**  
* @author dabai: 

* 类说明  RoleService接口
*/
public interface RoleService {
	public Integer addtRole(Role role);
	public Boolean updateRole(Role role);
	public Boolean deleteRole(Integer role_id);
	public List<Role>findActiveRoleList();
	public List<Role>findAllRoleList();
	public Role findRoleById(Integer role_id);

}
