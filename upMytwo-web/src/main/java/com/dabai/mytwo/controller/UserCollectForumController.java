package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.User.UserCollectForum;
import api.User.UserCollectForumService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dabai:
 * <p>
 * 类说明 UserCollectForum前端控制器
 */
@Controller
public class UserCollectForumController {

    @DubboReference
    private UserCollectForumService ucfService;
    HashMap<String, Object> map = new HashMap<String, Object>();

    // 添加收藏记录
    @RequestMapping("jwt/myCollectForum")
    @ResponseBody
    HashMap<String, Object> addCollect(@RequestBody UserCollectForum userCollectForum, HttpServletRequest request) {
        Map<String, Object> dataMap = (Map<String, Object>) request.getAttribute("data");
        String userid = (String) dataMap.get("userid");
        if (null == userCollectForum || null == userCollectForum.getForum_id() || StringUtils.isBlank(userid)) {
            map.put("code", 3);
            map.put("msg", "错误的请求,请检查是否登陆");
            return map;
        }
        UserCollectForum cf = new UserCollectForum();
        cf.setUser_id(userid);
        cf.setCreate_time(new Date());
        cf.setForum_id(userCollectForum.getForum_id());
        if (ucfService.addUserCollectForum(cf)) {
            map.put("code", 1);
            map.put("msg", "已添加");
            map.put("data", userCollectForum);
            return map;
        }
        map.put("code", 2);
        map.put("msg", "已添加到收藏请勿重复操作,或请求的forum不存在");
        return map;
    }

    // 更新收藏记录 可能取消 可能添加
    @RequestMapping("jwt/editmyCollectForum")
    @ResponseBody
    HashMap<String, Object> editCollect(@RequestBody UserCollectForum userCollectForum, HttpServletRequest request) {
        Map<String, Object> dataMap = (Map<String, Object>) request.getAttribute("data");
        String userid = (String) dataMap.get("userid");
        if (null == userCollectForum || null == userCollectForum.getForum_id()
                || StringUtils.isBlank(userid)) {
            map.put("code", 3);
            map.put("msg", "错误的请求,请检查是否登陆");
            return map;
        }
        userCollectForum.setUser_id(userid);
        if (ucfService.updateById(userCollectForum)) {
            map.put("code", 1);
            if (userCollectForum.getIs_collect())
                map.put("msg", "已添加到收藏");
            else
                map.put("msg", "已取消收藏");
            map.put("data", userCollectForum);
            return map;
        }
        map.put("code", 2);
        map.put("msg", "修改失败，刷新后重试");
        return map;
    }

    // 获取pageInfo信息
    @RequestMapping("user/getCollectCount")
    @ResponseBody
    PageInfo getPageInfo(@RequestBody PageInfo pageInfo) {
        if (null == pageInfo || StringUtils.isBlank(pageInfo.getUserid())) {
            pageInfo = new PageInfo();
            pageInfo.setUserid("test");
            pageInfo.setCurrentpage(1);
        }
        if (null == pageInfo.getCurrentpage() || pageInfo.getCurrentpage() < 1)
            pageInfo.setCurrentpage(1);
        if (null == pageInfo.getPagesize() || pageInfo.getPagesize() < 1)
            pageInfo.setPagesize(6);
        return ucfService.getPageInfo(pageInfo);
    }

    // 获取pageInfo信息
    @RequestMapping("jwt/getCollectForum")
    @ResponseBody
    HashMap<String, Object> getCollect(@RequestBody PageInfo pageInfo, HttpServletRequest request) {
        Integer currentPage = 1;
        Integer pageSize = 6;

        HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("data");

        String uid = (String) map.get("userid");
        map.clear();
        if (null == pageInfo || StringUtils.isBlank(pageInfo.getUserid())) {
            map.put("code", 3);
            map.put("msg", "错误的请求");
            return map;
        }
        if (!uid.equals(pageInfo.getUserid())) {
            map.put("code", 3);
            map.put("msg", "非法访问,请登陆后重试");
            return map;
        }
        if (null != pageInfo.getCurrentpage() && pageInfo.getCurrentpage() > 0)
            currentPage = pageInfo.getCurrentpage();
        if (null != pageInfo.getPagesize() && pageInfo.getPagesize() > 1)
            pageSize = pageInfo.getPagesize();


        List<ForumInfo> list = ucfService.findUserCollectForumList(uid, currentPage, pageSize);
        map.put("code", 1);
        map.put("msg", "共找到(" + list.size() + ")条记录");
        map.put("list", list);
        return map;


    }

    //查询某个forum是否收藏
    @RequestMapping("jwt/checkForumCollect")
    @ResponseBody
    HashMap<String, Object> checkForumCollect(Long forum_id, HttpServletRequest request) {
        if (null == forum_id) {
            map.put("code", 3);
            map.put("msg", "参数错误");
            return map;
        }
        HashMap<String, Object> map1 = (HashMap<String, Object>) request.getAttribute("data");
        String userid = (String) map1.get("userid");
        map1.clear();
        map.put("userid", userid);
        map.put("forumid", forum_id);

        Boolean iscollect = ucfService.checkForumIsCollect(map);
        map.clear();
        if (iscollect == null) {
            map.put("code", 3);
            map.put("msg", "forum is not collected");
            return map;
        } else if (!iscollect) {
            map.put("code", 2);
            map.put("msg", "forum canceled collect");
            return map;
        }
        map.put("code", 1);
        map.put("msg", "forum is collecting");
        return map;
    }

}
