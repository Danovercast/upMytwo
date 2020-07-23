package com.dabai.serviceImpl.Comment;


import com.dabai.dto.SomeInfo.CommentInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.SomeInfo.UserCommentInfo;
import api.Comment.CommentService;
import com.dabai.dao.Comment.CommentMapper;
import com.dabai.dto.Comment.Comment;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  commentService实现类
 */
@DubboService
@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Boolean insertComment(Comment comment) {
        return commentMapper.insertComment(comment) == 1;

    }

    @Override
    public Boolean deleteCommentBySelf(String comment_id, String user_id) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("comment_id", comment_id);
        map.put("user_id", user_id);
        return commentMapper.deleteCommentBySelf(map) == 1;

    }

    @Override
    public Boolean deleteCommentByForumId(Long forum_id) {
        return commentMapper.normalDeleteCommentByForumId(forum_id) == 1;

    }

    @Override
    public List<UserCommentInfo> findCommentListByUserId(String user_id, Integer currentPage, Integer pageSize) {
        Integer totalCount = commentMapper.getCountCommentByUserId(user_id);
        Integer totalPage = (int) Math.ceil((totalCount / pageSize + 0.4));
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        return commentMapper.findCommentListByUserId(user_id, (currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public Boolean deleteCommentById(String comment_id) {
        return commentMapper.deleteCommentById(comment_id) == 1;

    }

    @Override
    public int deleteCommentByIds(String[] commentIds) {
        return commentMapper.deleteCommentByIds(commentIds);
    }

    @Override
    public PageInfo getPageInfoByForumId(PageInfo pageInfo) {
        Integer count = commentMapper.getCountCommentByForumId(pageInfo.getForumid());
        pageInfo.setTotalcount(count);
        pageInfo.setTotalpage((int) Math.ceil((count / pageInfo.getPagesize() + 0.4)));
        if (pageInfo.getCurrentpage() >= pageInfo.getTotalpage()) {
            pageInfo.setCurrentpage(pageInfo.getTotalpage());
        }
        return pageInfo;
    }

    @Override
    public List<CommentInfo> findCommentListByForumId(Long forum_id, Integer currentPage, Integer pageSize) {
        Integer totalCount = commentMapper.getCountCommentByForumId(forum_id);
        Integer totalPage = (int) Math.ceil((totalCount / pageSize + 0.4));
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }
        return commentMapper.findCommentListByForumId(forum_id, (currentPage - 1) * pageSize, pageSize);

    }

    @Override
    public PageInfo getPageInfoByUserId(PageInfo pageInfo) {
        Integer count = commentMapper.getCountCommentByUserId(pageInfo.getUserid());
        pageInfo.setTotalcount(count);
        pageInfo.setTotalpage((int) Math.ceil(count / pageInfo.getPagesize() + 0.4));
        if (pageInfo.getCurrentpage() > pageInfo.getTotalpage())
            pageInfo.setCurrentpage(pageInfo.getTotalpage());
        return pageInfo;
    }

    @Override
    public List<CommentInfo> getCommentInfo(PageInfo pi) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Integer start = (int) Math.ceil((pi.getCurrentpage() - 1) * pi.getPagesize());

        map.put("start", start);
        map.put("end", pi.getPagesize());
        List<CommentInfo> list = commentMapper.getCommentInfoByPageInfo(map);

        return list;
    }

    @Override
    public PageInfo getCommentPageInfo(PageInfo pi) {
        Integer count = commentMapper.getCommentCount();
        Integer totalPage = (int) Math.ceil((count / pi.getPagesize() + 0.4));
        pi.setTotalpage(totalPage);
        pi.setTotalcount(count);
        return pi;
    }

    @Override
    public List<UserCommentInfo> searchComment(HashMap<String, Object> map) {
        return commentMapper.searchComment(map);
    }

}
