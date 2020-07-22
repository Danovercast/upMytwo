package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.SomeIds;
import api.Privilege.RolePrivilegeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author dabai:
 * 
 *         类说明 RolePrivilege前端控制器
 */
@Controller
public class RolePrivilegeController {

	@DubboReference
	private RolePrivilegeService rpService;
	HashMap<String, Object> map = new HashMap<String, Object>();

	// 添加role 和 privilege的联系
	@RequestMapping("manager/addRolePrivilegeRelation")
	@ResponseBody
	HashMap<String, Object> addRolePrivilege(@RequestBody SomeIds someIds) {
		if (null == someIds || null == someIds.getRoleid() || null == someIds.getIntergerids()||someIds.getIntergerids().length <1) {

			map.put("code", 3);
			map.put("msg", "错误的rolePrivilege信息,刷新后重试");
			return map;
		}

			if (rpService.addRolePrivilege(someIds.getRoleid(), someIds.getIntergerids())) {

				map.put("code", 1);
				map.put("msg", "添加成功");
				return map;
			}
		map.put("code", 2);
		map.put("msg", "添加失败,请确保role名称唯一.刷新后重试");
		return map;
	}

	@RequestMapping("manager/deleteRolePrivilegeRelation")
	@ResponseBody
	HashMap<String, Object> deletePrivilegeforRole(@RequestBody SomeIds someIds) {
		int length=someIds.getIntergerids().length;
		if (null == someIds || null == someIds.getRoleid() || null == someIds.getIntergerids()||length<1) {
			map.put("code", 3);
			map.put("msg", "错误的rolePrivilege信息,刷新后重试");
			return map;
		}
		if(rpService.deletePrivilegeForRole(someIds.getRoleid(), someIds.getIntergerids())==length){
			
		
			map.put("code", 1);
			map.put("msg", "删除成功");
			map.put("data", length);
			return map;
		}
		map.put("code",2);
		map.put("msg", "删除失败, 角色权限关系可能不存在或者已经删除");
		return map;
	}
	 
	

}
