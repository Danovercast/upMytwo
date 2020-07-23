package com.dabai.serviceImpl.User;

import api.User.UserCollectForumService;
import com.dabai.dao.User.UserCollectForumMapper;
import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.User.UserCollectForum;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  UserCollectForumService实现类
 */
@DubboService
public class UserCollectForumServiceImpl implements UserCollectForumService {

    @Autowired
    private UserCollectForumMapper userCollectForumMapper;

    @Override
    public Boolean addUserCollectForum(UserCollectForum userCollectForum) {
        userCollectForum.setIs_collect(true);
        userCollectForum.setCreate_time(new Date());
        return userCollectForumMapper.insertUserCollectForum(userCollectForum) == 1;

    }

    @Override
    public Boolean updateById(UserCollectForum userCollectForum) {
        return userCollectForumMapper.updateUserCollectForumById(userCollectForum) == 1;

    }

    @Override
    public List<ForumInfo> findUserCollectForumList(String user_id, Integer currentPage, Integer pageSize) {
        Integer totalCount = userCollectForumMapper.getCountByUserId(user_id);
        Integer totalPage = (int) Math.ceil(totalCount / pageSize + 0.4);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }

        return userCollectForumMapper.findUserCollectForumList(user_id, (currentPage - 1) * pageSize, pageSize);
    }

    @Override
    public PageInfo getPageInfo(PageInfo pageInfo) {
        Integer pageSize = pageInfo.getPagesize();
        Integer totalCount = userCollectForumMapper.getCountByUserId(pageInfo.getUserid());
        Integer currentPage = pageInfo.getCurrentpage();
        Integer totalPage = (int) Math.ceil(totalCount / pageSize + 0.4);
        if (currentPage > totalPage)
            pageInfo.setCurrentpage(totalPage);
        pageInfo.setTotalcount(totalCount);
        return pageInfo;
    }

    @Override
    public Boolean checkForumIsCollect(HashMap<String, Object> map) {

        Boolean isCollect = userCollectForumMapper.checkIsCollect(map);
        System.out.println(isCollect);
        return isCollect;
    }

}
