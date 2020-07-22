package com.dabai.serviceImpl.Forum;


import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import api.Forum.ForumService;
import com.dabai.dao.Comment.CommentMapper;
import com.dabai.dao.Forum.ForumMapper;
import com.dabai.dto.Forum.Forum;
import com.dabai.dto.Forum.HomeForum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
* @author dabai: 

* 类说明  forumService实现类
*/
@DubboService
public class ForumServiceImpl implements ForumService {

	@Autowired
	private ForumMapper forumMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Override
	public Long addForumGetForumId(Forum forum) {
		forumMapper.insertForum(forum);
		return forum.getForum_id();
	}

	@Override
	public Boolean deleteForumById(Long forum_id) {
		commentMapper.normalDeleteCommentByForumId(forum_id);
		return forumMapper.normalDeleteForumById(forum_id)==1;

	}

	@Override
	public List<Forum> findForumListByUserId(String user_id, Integer currentPage, Integer pageSize) {
		Integer totalCount=forumMapper.getForumCountByUserId(user_id);
		Integer totalPage=(int) Math.ceil(totalCount/pageSize+0.4);
		if(currentPage>=totalPage){
			currentPage=totalPage;
		}
		List<Forum> list = forumMapper.findForumListByUserId(user_id, (int) Math.ceil((currentPage-1)*pageSize),pageSize);
		return list;
	}

	@Override
	public List<ForumInfo> findForumListByInstitutionId(Long institution_id, Integer currentPage, Integer pageSize) {
		Integer totalCount=forumMapper.getForumCountByInstitutionId(institution_id);
		Integer totalPage= (int) Math.ceil(totalCount/pageSize+0.4);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("institution_id", institution_id);
		map.put("start", (currentPage-1)*pageSize);
		map.put("end", pageSize);
		List<ForumInfo> list = forumMapper.findForumListByInstitutionId(map);
		return list;
	}

	@Override
	public Boolean updateForum(Forum forum) {
		 return forumMapper.updateForum(forum)==1;
	}

	@Override
	public Boolean deleteForumBySelf(Forum forum) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("forum_id", forum.getForum_id());
		map.put("author_id", forum.getAuthor_id());
		commentMapper.normalDeleteCommentByForumId(forum.getForum_id());
		return forumMapper.normalDeleteForumSelf(map)==1;
	}

	@Override
	public List<ForumInfo> getMomentForum(Integer currentpage, Integer pagesize) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", (currentpage-1)*pagesize);
		map.put("end", pagesize);
		List<ForumInfo>list= forumMapper.getMomentForum(map);
		return list;
	}

	@Override
	public PageInfo getPageInfo(PageInfo pageinfo) {
		
		int totalCount = forumMapper.getCountByPageInfo(pageinfo);
		Integer totalPage=(int) Math.ceil(totalCount/pageinfo.getPagesize()+0.4);
		pageinfo.setTotalpage(totalPage);
		pageinfo.setTotalcount(totalCount);
		return pageinfo;
	}

	@Override
	public ForumInfo findForumInfoById(Long forumid) {
		
		return forumMapper.findForumById(forumid);
	}

	@Override
	public List<HomeForum> getHomeForumByUid(String uid) {
		return forumMapper.getHomeForumUserid(uid);
	}

	@Override
	public List<ForumInfo> getForumList(PageInfo pi) {
		Integer start=(int) Math.ceil((pi.getCurrentpage()-1)*pi.getPagesize());
		Integer end=pi.getPagesize();
		HashMap<String,Object>map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("end", end);
		List<ForumInfo>list=forumMapper.getForumList(map);
		return list;
	}

	@Override
	public HashMap<String, Object> findSearchForum(PageInfo pageinfo) {
		Integer end=pageinfo.getPagesize();
		Integer start=(pageinfo.getCurrentpage()-1)*end;
		String key=pageinfo.getCode();
		HashMap<String,Object>map=new HashMap<String,Object>();
		String userid = pageinfo.getUserid();
		if(StringUtils.isNotBlank(userid)) {
			map.put("userid", userid);
		}
		map.put("key", key);
		map.put("start", start);
		map.put("end", end);
		List<ForumInfo> list = forumMapper.findSearchForum(map);
		map.clear();
		int count = forumMapper.getCountByPageInfo(pageinfo);
		Integer totalpage=(int) Math.ceil((count/end+0.4));
		pageinfo.setTotalcount(count);
		pageinfo.setTotalpage(totalpage);
		map.put("list", list);
		map.put("pageinfo", pageinfo);
		return map;
	}

}
