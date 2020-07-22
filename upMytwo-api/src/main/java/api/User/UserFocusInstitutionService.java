package api.User;


import com.dabai.dto.SomeInfo.UserFocusInstitution;

import java.util.HashMap;

/**
* @author dabai: 

* 类说明  UserFocusInstitutionService接口
*/
public interface UserFocusInstitutionService {
	public Boolean addUserFocusInstitution(UserFocusInstitution userFocusInstitution);
	public Boolean updateByUserIdInstitutionId(UserFocusInstitution userFocusInstitution);
	public HashMap<String,Object> findUserFocusInstitutionContentByUserId(String user_id, Integer currentPage, Integer pageSize);

}
