package com.dabai.dao.Comment;



import com.dabai.dto.SomeInfo.CommentInfo;
import com.dabai.dto.SomeInfo.UserCommentInfo;
import com.dabai.dto.Comment.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
@Mapper
public interface CommentMapper {
	int insertComment(Comment comment);

	int deleteCommentBySelf(LinkedHashMap<String, Object> map);

	int normalDeleteCommentByForumId(Long forum_id);

	List<CommentInfo> findCommentListByForumId(Long forum_id, Integer start, Integer end);

	List<UserCommentInfo> findCommentListByUserId(String user_id, Integer start, Integer end);

	int deleteCommentById(String comment_id);

	int deleteCommentByIds(String[] commentIds);

	Integer getCountCommentByForumId(Long forum_id);

	Integer getCountCommentByUserId(String user_id);

	List<CommentInfo> getCommentInfoByPageInfo(HashMap<String, Object> map);

	Integer getCommentCount();

	List<UserCommentInfo> searchComment(HashMap<String, Object> map);

}