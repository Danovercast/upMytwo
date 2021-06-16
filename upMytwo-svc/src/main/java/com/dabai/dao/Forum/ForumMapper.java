package com.dabai.dao.Forum;


import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.Forum.Forum;
import com.dabai.dto.Forum.HomeForum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ForumMapper {
    public int insertForum(Forum forum);

    public int normalDeleteForumById(Long forum_id);

    public List<Forum> findForumListByUserId(@Param("userId")String user_id,@Param("start") Integer start,@Param("end") Integer end);

    public List<ForumInfo> findForumListByInstitutionId(Map<String, Object> map);

    public int updateForum(Forum forum);

    public Integer getForumCountByUserId(String user_id);

    public Integer getForumCountByInstitutionId(Long institution_id);

    public ForumInfo findForumById(Long forum_id);

    public int normalDeleteForumSelf(Map<String, Object> map);

    public List<ForumInfo> getMomentForum(Map<String, Object> map);

    public int getCountByPageInfo(PageInfo pageInfo);

    public List<HomeForum> getHomeForumUserid(String uid);

    public List<ForumInfo> getForumList(HashMap<String, Object> map);

    public List<ForumInfo> findSearchForum(HashMap<String, Object> map);
}