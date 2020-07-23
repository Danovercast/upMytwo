package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.LoginHistory;
import com.dabai.dto.SomeInfo.PageInfo;
import api.User.LoginHistoryService;
import com.dabai.dto.User.User;
import api.User.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author dabai:
 * <p>
 * 类说明 LonginHistory前端控制器
 */
@Controller
public class LonginHistoryController {

    @DubboReference
    private LoginHistoryService lHService;
    @DubboReference
    private UserService userService;
    String result = "";

    @RequestMapping("user/showMyLoginHistory")
    @ResponseBody
    HashMap<String, Object> showLoginHistoryList(PageInfo pageInfo) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == pageInfo || null == pageInfo.getCode() || StringUtils.isBlank(pageInfo.getUserid())) {
            map.put("code", 3);
            map.put("msg", "无信息，请尝试输入密码重试,访问敏感信息需要验证您的账户密码");
            return map;
        }
        User u = new User();
        u.setUser_id(pageInfo.getUserid());
        u.setPassword(pageInfo.getCode());
        String userId = userService.getUserIdByUserIdPassword(u);
        if (null != userId) {
            Integer currentPage = pageInfo.getCurrentpage();
            Integer pageSize = pageInfo.getPagesize();
            if (null == currentPage || currentPage < 1)
                currentPage = 1;
            if (null == pageSize || pageSize < 1)
                pageSize = 6;
            HashMap<String, Object> map2 = lHService.findUserLoginHistory(userId, currentPage, pageSize);
            if (null != map2.get("list")) {
                map2.put("code", 1);
                map2.put("msg", "登陆记录");
                return map2;
            } else {
                map.put("code", 1);
                map.put("msg", "暂无您的登陆记录");
                map.put("data", "记录为空");
                return map;
            }
        }
        map.put("code", 2);
        map.put("msg", "暂无查找记录 ,或者您的账号可能未激活");
        return map;
    }

    //删除记录
    @RequestMapping("user/deleteLoginHistory")
    @ResponseBody
    HashMap<String, Object> deleteLoginHistory(@RequestBody LoginHistory lh) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == lh || null == lh.getId() || null == lh.getUser_id() || StringUtils.isBlank(lh.getUser_id()) || StringUtils.isBlank(lh.getId())) {
            map.put("code", 3);
            map.put("msg", "删除记录失败,用户可能未登录 或验证信息过期");
            return map;
        }
        if (lHService.deleteLoginHistoryById(lh)) {
            map.put("code", 1);
            map.put("msg", "删除记录成功");
            return map;
        }
        map.put("code", 2);
        map.put("msg", "未知错误,请确保按步骤进行");
        return map;
    }


}
