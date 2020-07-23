package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.ForumInfo;
import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.Forum.Forum;
import api.Forum.ForumService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  forum前端控制器
 */
@Controller
public class ForumController {

    @DubboReference
    private ForumService forumService;
    String result = "";

    //创建Forum  成功返回forumid
    @RequestMapping("jwt/releaseForum")
    @ResponseBody
    public HashMap<String, Object> releaseForum(@RequestBody Forum forum, HttpServletRequest request) {
        HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("data");
        String userid = (String) map.get("userid");

        map.clear();
        if (null == forum || null == forum.getInstitution_id() || StringUtils.isBlank(userid)) {
            map.put("code", 3);
            map.put("msg", "存在错误信息,请检查信息重试,或者登录后重试");
            return map;
        }
        if (null == forum.getTitle() || StringUtils.isBlank(forum.getTitle())) {
            map.put("code", 2);
            map.put("msg", "发布内容标题不能为空，请重试");
            return map;
        }
        forum.setCreate_time(new Date());
        forum.setState(1);
        forum.setAuthor_id(userid);
        Long forumId = forumService.addForumGetForumId(forum);
        map.put("code", 1);
        map.put("msg", "发布成功");
        map.put("data", forumId);
        return map;
    }

    //逻辑删除forum  service层还会更新forum
    @RequestMapping("forum/deleteMyForum")
    @ResponseBody
    public HashMap<String, Object> deleteMyForum(@RequestBody Forum forum) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == forum || null == forum.getAuthor_id() || null == forum.getForum_id()) {
            map.put("code", 3);
            map.put("msg", "信息错误,请确定登陆后正确操作重试");
            return map;
        }
        if (forumService.deleteForumBySelf(forum)) {
            map.put("code", 1);
            map.put("msg", "删除成功");
            return map;
        }
        map.put("code", 2);
        map.put("msg", "删除forum不存在  或者已删除");
        return map;
    }

    //更新forum
    @RequestMapping("forum/updateMyForum")
    @ResponseBody
    public HashMap<String, Object> updateForum(@RequestBody Forum forum) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == forum || null == forum.getAuthor_id() || null == forum.getForum_id() || null == forum.getInstitution_id()) {
            map.put("code", 3);
            map.put("msg", "错误的forum信息,尝试登陆后重试");
            return map;
        }
        if (forumService.updateForum(forum)) {
            map.put("code", 1);
            map.put("msg", "更新成功");
            map.put("data", forum.getForum_id());
            return map;
        }
        map.put("code", 2);
        map.put("msg", "更新失败  网络延迟或forum不存在");
        return map;
    }

    //返回institution的forumlist
    @RequestMapping("forum/institutionForumList")
    @ResponseBody
    public List<ForumInfo> getInstitutionForumList(@RequestBody PageInfo pageInfo) {
        if (null == pageInfo || null == pageInfo.getInstitutionid())
            return null;
        Integer currentPage = pageInfo.getCurrentpage();
        Integer pageSize = pageInfo.getPagesize();
        Long instituionId = pageInfo.getInstitutionid();
        if (null == currentPage || currentPage < 0)
            currentPage = 1;
        if (null == pageSize || pageSize < 1 || pageSize > 10)
            pageSize = 8;
        List<ForumInfo> list = forumService.findForumListByInstitutionId(instituionId, currentPage, pageSize);
        return list;
    }

    //返回user的forumList
    @RequestMapping("forum/userForumList")
    @ResponseBody
    public List<Forum> getUserForumList(@RequestBody PageInfo pageInfo) {
        if (null == pageInfo || null == pageInfo.getUserid())
            return null;
        Integer currentPage = pageInfo.getCurrentpage();
        Integer pageSize = pageInfo.getPagesize();
        String userId = pageInfo.getUserid();
        if (null == currentPage || currentPage < 0)
            currentPage = 1;
        if (null == pageSize || pageSize < 1 || pageSize > 10)
            pageSize = 8;
        List<Forum> list = forumService.findForumListByUserId(userId, currentPage, pageSize);
        return list;
    }

    //manager逻辑删除forum  service层还会把相关的comment逻辑删除
    @RequestMapping("manager/deleteForumById")
    @ResponseBody
    public HashMap<String, Object> deleteForumById(Long forum_id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == forum_id) {
            map.put("code", 3);
            map.put("msg", "forum id 信息为空");
            return map;
        }
        if (forumService.deleteForumById(forum_id)) {
            map.put("code", 1);
            map.put("msg", "删除成功");
            map.put("data", forum_id);
            return map;
        }
        map.put("code", 2);
        map.put("msg", "删除失败   forum可能不存在 或已被删除");
        return map;
    }

    //首页显示forum
    @RequestMapping(value = "base/indexForum")
    @ResponseBody
    public List<ForumInfo> getIndexForum(Integer currentpage, Integer pagesize) {
        if (null == currentpage || currentpage < 1)
            currentpage = 1;
        if (null == pagesize || pagesize < 1)
            pagesize = 6;

        List<ForumInfo> ls = forumService.getMomentForum(currentpage, pagesize);
        return ls;
    }

    //封装PageInfo信息
    @RequestMapping("base/insForumPageInfo")
    @ResponseBody
    public PageInfo getInsPageInfo(PageInfo pageinfo) {
        if (null == pageinfo) {
            return null;
        }
        Long institutionid = pageinfo.getInstitutionid();
        String userid = pageinfo.getUserid();
        Integer pagesize = pageinfo.getPagesize();

        if (null == institutionid && StringUtils.isBlank(userid) && StringUtils.isBlank(pageinfo.getCode())) {
            return null;
        }
        if (null == pagesize || pagesize < 1)
            pageinfo.setPagesize(6);
        pageinfo.setCode(null);
        return forumService.getPageInfo(pageinfo);
    }

    //查看forum内容
    @RequestMapping("p/{forumid}")
    public String forumInfo(@PathVariable("forumid") Long forumid, Model model) {

        ForumInfo fo = forumService.findForumInfoById(forumid);

        if (null == fo || StringUtils.isBlank(fo.getTitle())) {
            model.addAttribute("result", "未找到有关记录");
            return "error";
        }
        String authorid = fo.getAuthorid();
        List<Forum> otherFo = forumService.findForumListByUserId(authorid, 1, 4);
        model.addAttribute("otherForum", otherFo);
        model.addAttribute("forumInfo", fo);
        return "forum/forum";
    }

    @RequestMapping("manager/forumPageInfo")
    @ResponseBody
    PageInfo backForumPageInf(@RequestBody PageInfo pi) {
        if (null == pi) {
            pi = new PageInfo();
        }
        Integer currentpage = pi.getCurrentpage();
        Integer pagesize = pi.getCurrentpage();
        if (null == currentpage || currentpage < 1)
            pi.setCurrentpage(1);
        if (null == pagesize || pagesize < 1)
            pi.setPagesize(8);
        PageInfo info = forumService.getPageInfo(pi);
        return info;

    }

    @RequestMapping("manager/backForum")
    public String getForumList(HttpServletRequest request, Model model) {
        PageInfo pi = new PageInfo();
        pi.setCurrentpage(1);
        pi.setPagesize(8);
        pi = forumService.getPageInfo(pi);
        List<ForumInfo> list = forumService.getForumList(pi);
        model.addAttribute("pageInfo", pi);
        model.addAttribute("list", list);
        return "back/backforum";
    }

    @RequestMapping("forum/justSearch")
    @ResponseBody
    HashMap<String, Object> getSearch(@RequestBody PageInfo pageinfo) {
        String code = pageinfo.getCode();
        Integer currentpage = pageinfo.getCurrentpage();
        Integer pagesize = pageinfo.getPagesize();
        if (StringUtils.isBlank(code))
            return new HashMap<>(0);
        if (null == currentpage || null == pagesize) {
            currentpage = 1;
            pagesize = 6;
        } else if (currentpage < 1)
            currentpage = 1;
        else if (pagesize < 1)
            pagesize = 1;
        else {

        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map = forumService.findSearchForum(pageinfo);
        return map;
    }

    //搜索用户forum
    @RequestMapping("jwt/searchMyForum")
    @ResponseBody
    HashMap<String, Object> searchMyForum(HttpServletRequest request, @RequestBody PageInfo pi) {
        HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("data");
        String userid = (String) map.get("userid");
        map.clear();
        if (StringUtils.isBlank(userid)) {
            map.put("code", 3);
            map.put("msg", "信息错误,请尝试重新登陆并重试");
            return map;
        }
        String code = pi.getCode();
        Integer currentpage = pi.getCurrentpage();
        Integer pagesize = pi.getPagesize();
        if (StringUtils.isBlank(code))
            return new HashMap<>(0);
        pi.setCurrentpage(1);
        pi.setPagesize(10);
        pi.setUserid(userid);
        map.clear();
        map = forumService.findSearchForum(pi);
        map.put("code", 1);
        return map;
    }


}
