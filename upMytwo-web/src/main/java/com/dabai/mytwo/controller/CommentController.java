package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.CommentInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.SomeInfo.UserCommentInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import com.dabai.dto.Comment.Comment;
import api.Comment.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**  
* @author dabai: 

* 类说明  comment前端控制器
*/
@Controller
public class CommentController {
	
	@DubboReference
	private CommentService commentService;
	String result="";
	//TODO id从验证信息中取 
	//创建comment
	@RequestMapping("jwt/releaseComment")
	@ResponseBody
    public HashMap<String,Object> releaseComment(@RequestBody Comment comment, HttpServletRequest request){
		HashMap<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> dataMap = (Map<String, Object>) request.getAttribute("data");
		String userid=(String) dataMap.get("userid");
		if(null==comment.getForum_id()|| StringUtils.isBlank(userid)){
			map.put("code", 3);
			map.put("msg", "请以正确的方式操作,如您未登录请登录后尝试");
			return map;
		}
		if(StringUtils.isBlank(comment.getDetails())){
			map.put("code", 2);
			map.put("msg", "发帖信息为空");
			return map;
		}
		comment.setResponser_id(userid);
		comment.setCreate_time(new Date());
		comment.setComment_id(UUID.randomUUID().toString().replace("-", ""));
		comment.setState(1);
		if(commentService.insertComment(comment)){
			
		
		map.put("code", 1);
		map.put("msg", "comment发布成功");
		return map;
		}
		map.put("code", 2);
		map.put("msg", "发送失败,请检查网络状态和登陆状态后,刷新重试");
		return map;
	}
	//删除当前用户的comment
	@RequestMapping("comment/delete")
	@ResponseBody
    public HashMap<String,Object>  deleteSelfComment(@RequestBody Comment comment){
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(null==comment.getComment_id()||null==comment.getResponser_id()||null==comment.getForum_id()){
			map.put("code", 3);
			map.put("msg", "删除的内容信息错误，可能不存在");
			return map;
		}
		if(commentService.deleteCommentBySelf(comment.getComment_id(), comment.getResponser_id())){
			map.put("code", 1);
			map.put("msg", "已删除");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "记录不存在,或者已经删除");
		return map;
	}
	//查看用户自己的commentList
	@RequestMapping("jwt/myComment")
	@ResponseBody
    public List<UserCommentInfo> showMyComment(@RequestBody PageInfo pageInfo){
		String userId=pageInfo.getUserid();
		Integer currentPage=pageInfo.getCurrentpage();
		Integer pageSize=pageInfo.getPagesize();
		if(null==pageInfo||null==userId){
			return null;
		}
		if(null==currentPage||currentPage<1){
			currentPage=1;
		}
		if(null==pageSize||pageInfo.getPagesize()<1||pageInfo.getPagesize()>11){
			pageSize=8;
		}
		return commentService.findCommentListByUserId(userId, currentPage,pageSize);
	}
	//查看某个Forum的Comment list
	@RequestMapping("comment/forumComment")
	@ResponseBody
    public List<CommentInfo> showForumComment(@RequestBody PageInfo pageInfo){
		Long forumId=pageInfo.getForumid();
		Integer currentPage=pageInfo.getCurrentpage();
		Integer pageSize=pageInfo.getPagesize();
		if(null==pageInfo||null==pageInfo.getForumid()){
			return null;
		}
		if(null==currentPage||currentPage<1)
			currentPage=1;
		if(null==pageSize||pageSize<1||pageSize>10)
			pageSize=8;
		return commentService.findCommentListByForumId(forumId, currentPage, pageSize);
	}
	@RequestMapping("manager/deleteComment")
	@ResponseBody
    public HashMap<String,Object>deleteCommentById(String commentId){
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(null==commentId|| StringUtils.isBlank(commentId)){
			map.put("code", 3);
			map.put("msg", "no commentId selected");
			return map;
		}
		if(commentService.deleteCommentById(commentId)){
			map.put("code",1);
			map.put("msg", "删除成功");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "comment 已被删除或不存在");
		return map;
	}
	//查询forum的分页信息 totalpage pagesize totalcount
	@RequestMapping("comment/forumCommentPageInfo")
	@ResponseBody
    public PageInfo getForumCommentPageInfo(@RequestBody PageInfo pageInfo){
		if(null==pageInfo||null==pageInfo.getForumid())
			return null;
		Integer currentpage=pageInfo.getCurrentpage();
		Integer pageSize=pageInfo.getPagesize();
		if(null==currentpage)
			pageInfo.setCurrentpage(1);
		if(null==pageSize)
			pageInfo.setPagesize(6);
		return commentService.getPageInfoByForumId(pageInfo);
	}
	//查询user的forum的分页信息 totalpage  pagesize totalcount
	@RequestMapping("comment/userCommentPageInfo")
	@ResponseBody
    public PageInfo getUserCommentPageInfo(@RequestBody PageInfo pageInfo){
		if(null==pageInfo||null==pageInfo.getUserid())
			return null;
		String userId=pageInfo.getUserid();
		Integer currentpage=pageInfo.getCurrentpage();
		Integer pageSize=pageInfo.getPagesize();
		if(null==userId)
			pageInfo.setUserid("test");
		if(null==currentpage)
			pageInfo.setCurrentpage(1);
		if(null==pageSize)
			pageInfo.setPagesize(8);
		return  commentService.getPageInfoByUserId(pageInfo);
		
	}
	//后台批量删除
	@RequestMapping("manager/deleteCommentByIds")
	public String deleteCommentByIds(String commentIds, Model model){
		if(null==commentIds){
			result="选择删除的id不符合规则  ，检查重试";
			
		}else{
			String[]comment_ids=commentIds.split(",");
			if(comment_ids.length>0) {
				int i = commentService.deleteCommentByIds(comment_ids);
				result ="删除       （"+i+" )条记录成功";
			}
			
		}
		model.addAttribute("result", result);
		return "back/backcomment";
	}
	@RequestMapping("manager/backComment")
	public String backComment(@RequestParam(required=false)Integer page, PageInfo pi, Model model) {
		if(null!=page) {
			PageInfo pa=new PageInfo();
			pa.setCurrentpage(page);
			pa.setPagesize(8);
		 
			PageInfo p=commentService.getCommentPageInfo(pa);
			List<CommentInfo>list=commentService.getCommentInfo(pa);
			model.addAttribute("list", list);
			model.addAttribute("pageInfo", p);
			return "back/backcomment";
		}
		if(null==pi) {
			pi=new PageInfo();
			pi.setCurrentpage(1);
			pi.setPagesize(8);	
		}
		Integer currentpage=pi.getCurrentpage();
		Integer pagesize=pi.getPagesize();
		if(null==currentpage||currentpage<1)
			pi.setCurrentpage(1);
		if(null==pagesize||pagesize<1)
			pi.setPagesize(8);
		PageInfo p=commentService.getCommentPageInfo(pi);
		List<CommentInfo>list=commentService.getCommentInfo(pi);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", p);
		return "back/backcomment";
	}
	//模糊查询用户自己的评论
	@RequestMapping("jwt/searchComment")
	@ResponseBody
    public HashMap<String,Object> searchMyComment(HttpServletRequest request, String key){
		HashMap<String,Object> map=(HashMap<String, Object>) request.getAttribute("data");
		String userid=(String) map.get("userid");
		map.clear();
		if(StringUtils.isBlank(userid)) {
			map.put("code", 3);
			map.put("msg", "信息错误,请尝试重新登陆并重试");
			return map;
		}
		map.put("userid", userid);
		map.put("key", key);
		List<UserCommentInfo> list = commentService.searchComment(map);
		map.clear();
		map.put("code", 1);
		map.put("msg", "共找到("+list.size()+"条记录)");
		map.put("list", list);
		return map;
	}

}
