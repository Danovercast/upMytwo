package api.User;


import com.dabai.dto.SomeInfo.OtherUserInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.User.SchoolUser;
import com.dabai.dto.User.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dabai:
 * 
 *         类说明 userService接口
 */
public interface UserService {
	Boolean addUser(User user);

	User updateActiveAndGetUser(User user);

	User findUserByUsername(String username);

	User findUserByEmail(String email);

	Boolean updateUserImage(User user);

	Boolean updateUserInfo(User user);

	Boolean checkUserExit(String username);

	String getUserIdByUserIdPassword(User u);

	User findUserByUsernameUserid(String username, String userid);

	Map<String, Object> getHomeInfo(String uid, String uname);

	OtherUserInfo findOtherUserById(String uid);

	PageInfo getPageInfo(PageInfo pi);

	List<OtherUserInfo> getUserList(PageInfo pageinfo);

	User findUserById(String uid);

	boolean authenticUser(SchoolUser su);


    HashMap<String, Object> letstest(String name);
}
