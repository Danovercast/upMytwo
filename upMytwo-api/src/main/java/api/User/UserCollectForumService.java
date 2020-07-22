package api.User;


import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.User.UserCollectForum;

import java.util.HashMap;
import java.util.List;

/**
 * @author dabai:
 * 
 *         类说明 UserCollectForumService接口
 */
public interface UserCollectForumService {
	Boolean addUserCollectForum(UserCollectForum userCollectForum);

	Boolean updateById(UserCollectForum userCollectForum);

	List<ForumInfo> findUserCollectForumList(String user_id, Integer currentPage, Integer pageSize);

	PageInfo getPageInfo(PageInfo pageInfo);

	Boolean checkForumIsCollect(HashMap<String, Object> map);

}
