package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.SomeInfo.UserFocusInstitution;
import api.User.UserFocusInstitutionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author dabai:
 * <p>
 * 类说明 UserFocusInstitution前端控制器
 */
@Controller
public class UserFocusInstitutionController {

    @DubboReference
    private UserFocusInstitutionService ufiService;
    HashMap<String, Object> map = new HashMap<String, Object>();

    // 添加UserFocusInstitution
    @RequestMapping("user/addFocusInstitution")
    @ResponseBody
    HashMap<String, Object> addUserFocusInstitution(@RequestBody UserFocusInstitution ufi) {
        if (null == ufi || null == ufi.getInstitution_id() || StringUtils.isBlank(ufi.getUser_id())) {
            map.put("code", 3);
            map.put("msg", "错误的添加信息,确保登陆后尝试");
            return map;
        }
        ufi.setFocus_start(new Date());
        ufi.setId(UUID.randomUUID().toString().replace("-", ""));
        ufi.setIs_focus(true);
        if (ufiService.addUserFocusInstitution(ufi)) {
            map.put("code", 1);
            map.put("msg", "添加成功");
            map.put("data", ufi);
            return map;
        }
        map.put("code", 2);
        map.put("msg", "未添加  未知错误,请刷新后重试");
        return map;
    }

    // 更新UserFocusInstitution 包括取消 和重新添加 取消添加endtime
    // 添加UserFocusInstitution
    @RequestMapping("user/updateFocusInstitution")
    @ResponseBody
    HashMap<String, Object> updateUserFocusInstitution(@RequestBody UserFocusInstitution ufi) {
        if (null == ufi || null == ufi.getInstitution_id() || StringUtils.isBlank(ufi.getUser_id())) {
            map.put("code", 3);
            map.put("msg", "错误的添加信息,确保登陆后尝试");
            return map;
        }
        // 判断操作是重新添加还是取消收藏
        if (ufi.isIs_focus()) {

        } else {
            ufi.setFocus_end(new Date());
        }
        if (ufiService.updateByUserIdInstitutionId(ufi)) {
            map.put("code", 1);
            map.put("msg", "更新成功");
            map.put("data", ufi);
            return map;
        }
        map.put("code", 2);
        map.put("msg", "未添加  未知错误,请刷新后重试");
        return map;
    }

    // 查询所有 关注的
    @RequestMapping("user/findFocusInstitution")
    @ResponseBody
    HashMap<String, Object> findFocus(PageInfo pageInfo) {
        Integer currentPage = pageInfo.getCurrentpage();
        Integer pageSize = pageInfo.getPagesize();
        String userid = pageInfo.getUserid();
        if (null == pageInfo || StringUtils.isBlank(userid)) {
            return null;
        }
        if (null == currentPage || currentPage < 1)
            currentPage = 1;
        if (null == pageSize || pageSize < 1)
            pageSize = 6;
        HashMap<String, Object> map2 = ufiService.findUserFocusInstitutionContentByUserId(userid, currentPage, pageSize);
        return map2;

    }

}
