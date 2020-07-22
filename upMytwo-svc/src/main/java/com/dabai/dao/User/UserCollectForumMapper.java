package com.dabai.dao.User;



import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.User.UserCollectForum;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface UserCollectForumMapper {
	public int insertUserCollectForum(UserCollectForum userCollectForum);
	public int updateUserCollectForumById(UserCollectForum userCollectForum);
    public List<ForumInfo> findUserCollectForumList(String user_id, Integer start, Integer end);
	public Integer getCountByUserId(String user_id);
	public Boolean checkIsCollect(HashMap<String, Object> map);
}