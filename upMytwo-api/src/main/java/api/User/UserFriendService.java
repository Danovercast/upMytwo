package api.User;

import com.dabai.dto.User.UserFriend;

import java.util.HashMap;

/**
 * @author dabai:
 * 
 *         类说明 UserFriendService接口
 */
public interface UserFriendService {
	 Boolean addUserFriend(UserFriend userFriend);

	 HashMap<String, Object> findSend(String user_id, Integer currentPage, Integer pageSize);

	 HashMap<String, Object> findGet(String user_id, Integer currentPage, Integer pageSize);

	 Boolean updateUserFriend(UserFriend userFriend);

	 boolean checkIsFriend(String theid, String checkid);

	HashMap<String, Object> getMyFriendInfo(String uid, Integer currentpage, Integer pagesize);

}
