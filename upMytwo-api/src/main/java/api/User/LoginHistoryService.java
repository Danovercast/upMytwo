package api.User;



import com.dabai.dto.SomeInfo.LoginHistory;

import java.util.HashMap;

/**  
* @author dabai: 

* 类说明  LoginHistory接口
*/
public interface LoginHistoryService {
	public Boolean addLonginHistory(LoginHistory loginHistory);
	public Boolean deleteLoginHistoryById(LoginHistory loginHistory);
	public HashMap<String,Object> findUserLoginHistory(String user_id, Integer currentPage, Integer pageSize);

}
