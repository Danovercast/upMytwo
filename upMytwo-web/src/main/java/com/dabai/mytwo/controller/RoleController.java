package com.dabai.mytwo.controller;

import com.dabai.dto.Privilege.Role;
import api.Privilege.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**  
* @author dabai: 

* 类说明   Role前端控制器
*/
@Controller
public class RoleController {

	@DubboReference
	private RoleService roleService;
	HashMap<String,Object>map=new HashMap<String,Object>();
	
	//添加role
	@RequestMapping("manager/addRole")
	@ResponseBody
    HashMap<String,Object> addRole(Role role){
		if(null==role||null==role.getRole_name()||null==role.getPrivilege_percent()){
			map.put("code", 3);
			map.put("msg", "role 信息错误 添加失败");
			return map;
		}
		Integer roleId = roleService.addtRole(role);
		if(null!=roleId){
			map.put("code", 1);
			map.put("msg", "添加role成功");
			map.put("data",roleId);
			return map;
		}
		map.put("code",2);
		map.put("msg", "role  添加失败,请重试");
		return map;
	}
	//更新role信息
	@RequestMapping("manager/editRole")
	@ResponseBody
    HashMap<String,Object> editRole(Role role){
		if(null==role||null==role.getRole_name()||null==role.getPrivilege_percent()||null==role.getRole_id()){
			map.put("code", 3);
			map.put("msg", "role 信息错误 添加失败");
			return map;
		}
		if(roleService.updateRole(role)){
			map.put("code", 1);
			map.put("msg", "更新role成功");
			map.put("data", role);
			return map;
		}
		map.put("code", 2);
		map.put("msg", "role更新失败,请确保role的名字唯一然后或者刷新后重试");
		return map;
	} 
	//逻辑删除role  set isactive=0
	@RequestMapping("manager/deleteRole")
	@ResponseBody
    HashMap<String,Object> deleteRole(Integer role_id){
		if(null==role_id){
			map.put("code", 3);
			map.put("msg", "删除失败 选择内容为空");
			return map;
		}
		if(roleService.deleteRole(role_id)){
			map.put("code", 1);
			map.put("msg", "删除成功");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "role 删除失败  该角色已经停用或者删除，请刷新重试");
		return map;
	}
	//查询所有在用的role
	@RequestMapping("manager/findActiveRoleList")
	public  String findActiveRole(Model model){
		 List<Role> list = roleService.findActiveRoleList();
		 model.addAttribute("list", list);
		 return "back/backrole";
	}
	//查询所有role
	@RequestMapping("manager/backRole")
	 public String findRoleList(Model model){
		 List<Role> list = roleService.findAllRoleList();
		 model.addAttribute("list", list);
		 return "back/backrole";
		 
	}
	//根据id查询某个Role
	@RequestMapping("manager/findRoleById")
	@ResponseBody
    Role findRoleById(Integer role_id){
		return roleService.findRoleById(role_id);
	}
	 
	
	
		

}
