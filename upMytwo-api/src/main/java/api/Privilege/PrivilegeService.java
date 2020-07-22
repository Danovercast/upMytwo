package api.Privilege;

import com.dabai.dto.Privilege.Privilege;

import java.util.List;

/**  
* @author dabai: 

* 类说明  PrivilegeService接口
*/
public interface PrivilegeService {
	public Boolean insertPrivilege(Privilege privilege);
	public Boolean updatePrivilege(Privilege privilege);
	public List<Privilege> findAllPrivilege();

}
