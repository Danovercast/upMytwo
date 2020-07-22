package com.dabai.mytwo.controller;

import com.dabai.dto.Privilege.Privilege;
import api.Privilege.PrivilegeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**  
* @author dabai: 

* 类说明  privilege前端控制器
*/
@Controller
public class PrivilegeController {

	@DubboReference
	private PrivilegeService priService;
	
	HashMap<String,Object>map=new HashMap<String,Object>();	
	
	//添加权限privilege
	@RequestMapping("manager/addPrivilege")
	@ResponseBody
    HashMap<String,Object> addPrivilege(@RequestBody Privilege privilege){
		if(null!=privilege&& StringUtils.isNotBlank(privilege.getPrivilege_name())){
			privilege.setCreate_time(new Date());
			privilege.setLast_edit(new Date());
			if(priService.insertPrivilege(privilege)){
				map.put("code", 1);
				map.put("msg", "添加成功");
				map.put("data", privilege);
				return map;
			}
			map.put("code", 2);
			map.put("msg", "添加失败,未知错误");
			return map;
		}
		map.put("code", 3);
		map.put("msg", "添加失败，错误的privilege信息");
		return map;
	}
	//更新privilege信息
	@RequestMapping("manager/editPrivilege")
	@ResponseBody
    HashMap<String,Object> editPrivilege(@RequestBody Privilege privilege){
		if(null!=privilege&& StringUtils.isNotBlank(privilege.getPrivilege_name())&&null!=privilege.getPrivilege_id()){
			privilege.setLast_edit(new Date());
			if(priService.updatePrivilege(privilege)){
				map.put("code", 1);
				map.put("msg", "更新成功");
				map.put("data", privilege);
				return map;
			}
			map.put("code", 2);
			map.put("msg", "更新失败,请检查信息并确保privilege的名字唯一然后重试");
			return map;
		}
		map.put("code", 3);
		map.put("msg", "更新失败，错误的privilege信息");
		return map;
	}
	//查询返回所有的privilege
	@RequestMapping("manager/backPrivilege")
	public String findAllPriviege(Model model){
		
		 List<Privilege> list = priService.findAllPrivilege();
		 model.addAttribute("list", list);
		 return "back/backprivilege";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
