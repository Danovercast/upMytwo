package com.dabai.serviceImpl.User;

import api.User.UserFriendService;
import com.dabai.dao.User.UserFriendMapper;
import com.dabai.dto.SomeInfo.FriendInfo;
import com.dabai.dto.SomeInfo.OtherUserInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.User.UserFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**  
* @author dabai: 

* 类说明  UserFriendService实现类
*/ 
@DubboService
public class UserFriendServiceImpl implements UserFriendService {
	@Autowired
	private UserFriendMapper userFriendMapper;

	@Override
	public Boolean addUserFriend(UserFriend userFriend) {
		userFriend.setCreate_time(new Date());
		userFriend.setIgnorethis(false);
		userFriend.setId(UUID.randomUUID().toString().replace("-", ""));
		userFriend.setIs_friend(false);
		return userFriendMapper.insertUserFriend(userFriend)==1;
	}

	@Override
	public HashMap<String,Object> findSend(String user_id,Integer currentPage,Integer pageSize) {
		HashMap<String,Object>map=new HashMap<String,Object>();
		Integer totalCount=userFriendMapper.getSendCount(user_id);
		Integer totalPage=(int) Math.ceil(totalCount/pageSize+0.4);
		if(currentPage>totalPage)
			currentPage=totalPage;
		map.put("user_id", user_id);
		map.put("start", (currentPage-1)*pageSize);
		map.put("end", pageSize);
		List<FriendInfo> list = userFriendMapper.findSend(map);
		map.clear();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("list", list);
		return map;

	}

	@Override
	public HashMap<String,Object> findGet(String user_id,Integer currentPage,Integer pageSize) {
		HashMap<String,Object>map=new HashMap<String,Object>();
		Integer totalCount=userFriendMapper.getGetCount(user_id);
		Integer totalPage=(int) Math.ceil(totalCount/pageSize+0.4);
		if(currentPage>totalPage)
			currentPage=totalPage;
		map.put("user_id", user_id);
		map.put("start", (currentPage-1)*pageSize);
		map.put("end", pageSize);
		List<FriendInfo> list = userFriendMapper.findGet(map);
		map.clear();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("list", list);
		return map;
	}
	@Override
	public Boolean updateUserFriend(UserFriend userFriend) {
		return userFriendMapper.updateUserFriend(userFriend)==1;

	}
	@Override
	public boolean checkIsFriend(String theid, String checkid) {
		return userFriendMapper.findRealByUid(theid,checkid)==1;
	}
	@Override
	public HashMap<String,Object> getMyFriendInfo(String uid, Integer currentpage, Integer pagesize) {
		HashMap<String,Object>map=new HashMap<String,Object>();
		Integer totalcount=userFriendMapper.getMyFriendCount(uid);
		Integer totalpage=(int) Math.ceil((totalcount/pagesize+0.4));
		PageInfo pi=new PageInfo();
		pi.setCurrentpage(currentpage);
		pi.setPagesize(pagesize);
		pi.setTotalcount(totalcount);
		pi.setTotalpage(totalpage);
		map.put("userid",uid);
		map.put("start",(currentpage-1)*pagesize);
		map.put("end", pagesize);
		List<OtherUserInfo>list=userFriendMapper.findFriendInfo(map);
		map.clear();
		map.put("pageinfo", pi);
		map.put("list", list);
		return map;
	}
}
