package com.dabai.serviceImpl.User;

import com.dabai.dto.SomeInfo.OtherUserInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import api.User.UserService;
import com.dabai.dao.Forum.ForumMapper;
import com.dabai.dao.User.UserMapper;
import com.dabai.dto.User.SchoolUser;
import com.dabai.dto.User.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;
import com.dabai.dto.Forum.HomeForum;

import java.util.*;

/**
 * @author dabai:
 * 
 *         类说明 userService 实现类
 */
@DubboService
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ForumMapper forumMapper;

	// 插入用户 用户id用uuid
	@Override
	public Boolean addUser(User user) {
		user.setRole_id(11001);
		user.setActivecode(UUID.randomUUID().toString().replace("-", ""));
		user.setUser_id(UUID.randomUUID().toString().replace("-", ""));
		user.setRegister(new Date());
		return userMapper.insertUser(user) == 1;
		// 创建邮件信息 并且发送邮件
		// TODO

	}

	@Override
	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}

	@Override
	public User findUserByEmail(String email) {
		return userMapper.findUserByEmail(email);
	}

	@Override
	public User updateActiveAndGetUser(User user) {
		userMapper.updateUserActive(user);
		return userMapper.findUserByFirst(user);

	}

	@Override
	public Boolean updateUserImage(User user) {
		return userMapper.updateUser(user) == 1;

	}

	@Override
	public Boolean updateUserInfo(User user) {
		return userMapper.updateUser(user) == 1;

	}

	@Override
	public Boolean checkUserExit(String username) {
		return userMapper.checkUsername(username) == 1;
	}

	@Override
	public String getUserIdByUserIdPassword(User u) {
		return userMapper.getUserIdByUsernamePassword(u);
	}

	@Override
	public User findUserByUsernameUserid(String username, String userid) {

		return userMapper.findUserByNameId(username, userid);
	}

	@Override
	public Map<String, Object> getHomeInfo(String uid, String uname) {
		User findU = null;
		if (StringUtils.isBlank(uname)) {
			findU = userMapper.findUserById(uid);
		} else {
			findU = userMapper.findUserByNameId(uname, uid);
		}
		if (null == findU)
			return null;
		List<HomeForum> list = forumMapper.getHomeForumUserid(uid);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user", findU);
		map.put("list", list);
		return map;
	}

	@Override
	public OtherUserInfo findOtherUserById(String uid) {
		return userMapper.findOtherUser(uid);
	}

	@Override
	public PageInfo getPageInfo(PageInfo pi) {
		Integer totalCount = userMapper.getUserCount();
		Integer totalPage = Math.round(totalCount / pi.getPagesize());
		pi.setTotalcount(totalCount);
		pi.setTotalpage(totalPage);
		return pi;
	}

	@Override
	public List<OtherUserInfo> getUserList(PageInfo pageinfo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageinfo.getCurrentpage() - 1) * pageinfo.getPagesize());
		map.put("end", pageinfo.getPagesize());

		List<OtherUserInfo> list = userMapper.findUserList(map);
		return list;
	}

	@Override
	public User findUserById(String uid) {
		return userMapper.findUserById(uid);
	}

	@Override
	public boolean authenticUser(SchoolUser su) {
		return userMapper.authenticSchoolUser(su)==1;
	}

}
