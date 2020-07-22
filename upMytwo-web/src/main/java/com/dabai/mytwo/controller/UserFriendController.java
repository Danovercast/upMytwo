package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.User.User;
import com.dabai.dto.User.UserFriend;
import api.User.UserFriendService;
import com.dabai.mytwo.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**  
* @author dabai: 

* 类说明  UserFriend前端控制器
*/
@Controller
public class UserFriendController {

	@DubboReference
	private UserFriendService ufService;
	HashMap<String,Object>map=new HashMap<String,Object>();
	//查找发送的申请
	@RequestMapping("jwt/findSend")
	@ResponseBody
    HashMap<String,Object> findSend(HttpServletRequest request, @RequestBody PageInfo pageInfo){
		map=(HashMap<String, Object>) request.getAttribute("data");
		String userid=(String) map.get("userid");
		map.clear();
		if(null==pageInfo||null==pageInfo.getUserid()||!userid.equals(pageInfo.getUserid())){
			map.put("code", 3);
			map.put("msg", "验证信息可能过期,请尝试重新登陆或刷新后重试");
			return map;
		}
		Integer currentPage=pageInfo.getCurrentpage();
		Integer pageSize=pageInfo.getPagesize();
		if(null==currentPage||currentPage<1)
			currentPage=1;
		if(null==pageSize||pageSize<1)
			pageSize=8;
		map=ufService.findSend(pageInfo.getUserid(),currentPage,pageSize);
		map.put("code", 1);
		return map;
	}
	//查找收到的申请
	@RequestMapping("jwt/findGet")
	@ResponseBody
    HashMap<String,Object> findGet(HttpServletRequest request, @RequestBody PageInfo pageInfo){
		map=(HashMap<String, Object>) request.getAttribute("data");
		String userid=(String) map.get("userid");
		map.clear();
		if(null==pageInfo||null==pageInfo.getUserid()||!userid.equals(pageInfo.getUserid())){
			map.put("code", 3);
			map.put("msg", "验证信息可能过期,请尝试重新登陆或刷新后重试");
			return map;
		}
		Integer currentPage=pageInfo.getCurrentpage();
		Integer pageSize=pageInfo.getPagesize();
		if(null==currentPage||currentPage<1)
			currentPage=1;
		if(null==pageSize||pageSize<1)
			pageSize=8;
		 map = ufService.findGet(pageInfo.getUserid(),currentPage,pageSize);
		 map.put("code", 1);
		 return map;
	}
	//更新UserFriend信息
	@RequestMapping("jwt/updatesendFriend")
	@ResponseBody
    HashMap<String,Object> updatesendFriend(HttpServletRequest request, @RequestBody UserFriend userFriend){
		map=(HashMap<String, Object>) request.getAttribute("data");
		String userid=(String) map.get("userid");
		userFriend.setUser1_id(userid);
		map.clear();
		if(null==userFriend|| StringUtils.isBlank(userFriend.getUser1_id())){
			map.put("code", 3);
			map.put("msg", "错误的userFriend信息");
			return map;
		}
		if(userFriend.getIs_friend()==null){
			map.put("code", 2);
			map.put("msg", "更新好友失败,请重新尝试");
			return map;
		}
		if(userFriend.getIs_friend())
			userFriend.setAdmit_time(new Date());
		if(ufService.updateUserFriend(userFriend)){
			map.put("code", 1);
			map.put("msg", "更新成功");
			map.put("data", userFriend);
			return map;
		}
		map.put("code", 2);
		map.put("msg", "更新失败,请重新尝试");
		return map;
	}
	//更新UserFriend信息
	@RequestMapping("jwt/updategetFriend")
	@ResponseBody
    HashMap<String,Object> updategetFriend(HttpServletRequest request, @RequestBody UserFriend userFriend){
		map=(HashMap<String, Object>) request.getAttribute("data");
		String userid=(String) map.get("userid");
		userFriend.setUser2_id(userid);
		map.clear();
		if(null==userFriend||StringUtils.isBlank(userFriend.getUser2_id())){
			map.put("code", 3);
			map.put("msg", "错误的userFriend信息");
			return map;
		}
		if(userFriend.getIs_friend()==null){
			map.put("code", 2);
			map.put("msg", "更新好友失败,请重新尝试");
			return map;
		}
		if(userFriend.getIs_friend())
			userFriend.setAdmit_time(new Date());
		if(ufService.updateUserFriend(userFriend)){
			map.put("code", 1);
			map.put("msg", "更新成功");
			map.put("data", userFriend);
			return map;
		}
		map.put("code", 2);
		map.put("msg", "更新失败,请重新尝试");
		return map;
	}
	//添加 朋友信息
	@RequestMapping("jwt/sendFriend")
	@ResponseBody
    HashMap<String,Object> addFriend(@RequestBody UserFriend userFriend, HttpServletRequest request){
		HashMap<String,Object>map=(HashMap<String, Object>) request.getAttribute("data");
		String user1id=(String) map.get("userid");
		userFriend.setUser1_id(user1id);
		if(null==userFriend|| StringUtils.isBlank(userFriend.getUser1_id())||StringUtils.isBlank(userFriend.getUser2_id())){
			map.put("code", 3);
			map.put("msg", "错误的userFriend信息");
			return map;
		}
		if(ufService.addUserFriend(userFriend)){
			map.put("code", 1);
			map.put("msg", "发送请求成功");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "发送失败，是否重复添加?请勿重复添加,或者稍后刷新重试");
		return map;
	}
	@RequestMapping("user/myFriend")
	public String myFriend(@RequestParam(required=true)String info, @RequestParam(required=false)Integer currentpage, Model model) {
		if(null==currentpage||currentpage<1)
			currentpage=1;
		Integer pagesize=6;
		String result="";
		if(!JwtUtil.verifyToken(info)){
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		String uid=(String) JwtUtil.parseToken(info).get("userid");
		String username=(String) JwtUtil.parseToken(info).get("username");
		String image=(String) JwtUtil.parseToken(info).get("image");
		String signature=(String) JwtUtil.parseToken(info).get("signature");
		User u=new User();
		u.setImage(image);
		u.setUsername(username);
		u.setSignature(signature);
		u.setUser_id(uid);
		model.addAttribute("user", u);
		HashMap<String,Object>map=ufService.getMyFriendInfo(uid,currentpage,pagesize);
		model.addAttribute("map", map);
		return "user/friend";
	}

	@RequestMapping("jwt/checkUserFriend")
	@ResponseBody
    HashMap<String,Object> checkUf(String checkid, HttpServletRequest request){
		HashMap<String,Object> map=(HashMap<String, Object>) request.getAttribute("data");
		String theid=(String) map.get("userid");
		if(StringUtils.isBlank(checkid)||StringUtils.isBlank(theid)) {
			map.put("code", 3);
			map.put("msg", "some thing wrong");
			return map;
		}
		if(ufService.checkIsFriend(theid,checkid)) {
			map.put("code", 1);
			map.put("msg", "Yes");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "No");
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
