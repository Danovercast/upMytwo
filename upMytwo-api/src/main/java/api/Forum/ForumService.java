package api.Forum;



import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.Forum.Forum;
import com.dabai.dto.Forum.HomeForum;

import java.util.HashMap;
import java.util.List;

/**
 * @author dabai:
 * 
 *         类说明 forumService接口
 */
public interface ForumService {
	Long addForumGetForumId(Forum forum);

	Boolean deleteForumById(Long forum_id);

	List<Forum> findForumListByUserId(String user_id, Integer currentPage, Integer pageSize);

	List<ForumInfo> findForumListByInstitutionId(Long Institution_id, Integer currentPage, Integer pageSize);

	Boolean updateForum(Forum forum);

	Boolean deleteForumBySelf(Forum forum);

	List<ForumInfo> getMomentForum(Integer currentpage, Integer pagesize);

	PageInfo getPageInfo(PageInfo pageinfo);

	ForumInfo findForumInfoById(Long forumid);

	List<HomeForum> getHomeForumByUid(String uid);

	List<ForumInfo> getForumList(PageInfo pi);

	HashMap<String, Object> findSearchForum(PageInfo pageinfo);
}
