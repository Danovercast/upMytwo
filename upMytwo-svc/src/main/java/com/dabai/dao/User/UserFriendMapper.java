package com.dabai.dao.User;



import com.dabai.dto.SomeInfo.FriendInfo;
import com.dabai.dto.SomeInfo.OtherUserInfo;
import com.dabai.dto.User.UserFriend;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface UserFriendMapper {
	int insertUserFriend(UserFriend userFriend);

	List<FriendInfo> findSend(HashMap<String, Object> map);

	List<FriendInfo> findGet(HashMap<String, Object> map);

	int updateUserFriend(UserFriend userFriend);

	Integer getSendCount(String user_id);

	Integer getGetCount(String user_id);

	int findRealByUid(String theid, String checkid);

	Integer getMyFriendCount(String uid);

	List<OtherUserInfo> findFriendInfo(HashMap<String, Object> map);
}