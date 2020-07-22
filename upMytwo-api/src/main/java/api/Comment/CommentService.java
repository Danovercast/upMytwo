package api.Comment;


import com.dabai.dto.SomeInfo.CommentInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.SomeInfo.UserCommentInfo;
import com.dabai.dto.Comment.Comment;

import java.util.HashMap;
import java.util.List;

/**
 * @author dabai:
 * 
 *         类说明 commentService接口
 */
public interface CommentService {
	Boolean insertComment(Comment comment);

	Boolean deleteCommentBySelf(String comment_id, String user_id);

	Boolean deleteCommentByForumId(Long forum_id);

	List<UserCommentInfo> findCommentListByUserId(String user_id, Integer currentPage, Integer pageSize);

	Boolean deleteCommentById(String comment_id);

	int deleteCommentByIds(String[] commentIds);

	PageInfo getPageInfoByForumId(PageInfo pageInfo);

	List<CommentInfo> findCommentListByForumId(Long forum_id, Integer currentPage, Integer pageSize);

	PageInfo getPageInfoByUserId(PageInfo pageInfo);

	List<CommentInfo> getCommentInfo(PageInfo pi);

	PageInfo getCommentPageInfo(PageInfo pi);

	List<UserCommentInfo> searchComment(HashMap<String, Object> map);

}
