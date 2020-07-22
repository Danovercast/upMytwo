package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.*;
import api.Comment.CommentService;
import api.Comment.FeedBackService;
import org.apache.dubbo.config.annotation.DubboReference;
import com.dabai.dto.Eassy.Essay;
import api.Eassy.EssayService;
import api.Forum.ForumService;
import com.dabai.dto.Forum.HomeForum;
import com.dabai.dto.User.SchoolUser;
import com.dabai.dto.User.User;
import api.User.UserService;
import com.dabai.mytwo.util.JwtUtil;
import com.dabai.mytwo.util.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author dabai:
 * 
 *         类说明 userController前端控制器
 */
@Controller
public class UserController {
	private String result = "";
	@DubboReference
	private UserService userService;
	@DubboReference
	private CommentService commentService;
	@DubboReference
	private ForumService forumService;
	@DubboReference
	private EssayService essayService;
	@DubboReference
	private FeedBackService fbService;

	// 添加用户
	@RequestMapping("base/register")
	@ResponseBody
	public HashMap<String, Object> registerUser(User user, Model model) {
		HashMap<String, Object>map=new HashMap<String, Object>();
		int code=3;
		if (userService.checkUserExit(user.getUsername())) {
			result = "用户名不可用";
		}
		if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getUsername())|| StringUtils.isBlank(user.getPassword())) {
			result = "邮箱和用户名,密码均不能为空，请重试";
		} else{
			
		
			user.setPassword(Md5Utils.toMd5(user.getPassword(), user.getUsername(), 2));
			if (userService.addUser(user)){
				code=1;
				result = "注册成功，请在邮箱中确认您的信息，并激活账户(如未收到，请确认垃圾邮箱中是否有)";
			}
			else
			result = "注册可能未成功。。。。";
		}
		map.put("code", code);
		map.put("msg", result);
		return map;
		}

	// 用户登录
	@RequestMapping("base/login")
	@ResponseBody
	public HashMap<String, Object> userLogin(String username, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(username==null||password==null){
			map.put("code", 2);
			map.put("msg", "错误的用户信息，请检查重试");
			return map;
		}
		User checkU = userService.findUserByUsername(username);
		
		//执行普通的登陆逻辑
		password=Md5Utils.toMd5(password, username, 2);
		if (checkU == null || !password.equals(checkU.getPassword())) {
			// 用户信息错误
			map.put("code", 2);
			map.put("msg", "错误的用户信息，请检查重试");
			return map;
		}
		map.put("code", 1);
		map.put("userid", checkU.getUser_id());
		map.put("username", username);
		map.put("roleid", checkU.getRole_id());
		map.put("image",checkU.getImage());
		map.put("signature",checkU.getSignature());
		if(checkU.getSignature()!=null){
			map.put("signature", checkU.getSignature());
		}else{
			map.put("signature", "未设置个人说明");
		}
		
		String jwt = JwtUtil.createToken(map);
		Subject subject = SecurityUtils.getSubject();
		map.put("data", jwt);
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
		try {
			subject.login(usernamePasswordToken);
			return map;
		} catch (Exception e) {
			map.put("code", 3);
			map.put("msg", "异常，请重新尝试");
			return map;
		}
	}

	// 修改用户基本信息
	@RequestMapping("jwt/editUser")
	@ResponseBody
	public  HashMap<String, Object>  editUser(@RequestBody User u, HttpServletRequest request) {
		HashMap<String,Object> map=(HashMap<String, Object>) request.getAttribute("data");
		String userid=(String) map.get("userid");
		u.setUser_id(userid);
		String passwd=u.getPassword();
		map.clear();
		if (u == null || u.getUser_id() == null || StringUtils.isBlank(u.getUsername())|| StringUtils.isBlank(passwd)) {
			map.put("code", 3);
			map.put("msg", "用户信息错误,请确保登陆有效和用户名正确");
			return map;
		}
		
		u.setRole_id(null);
		u.setPassword(Md5Utils.toMd5(passwd, u.getUsername(), 2));
		if (userService.updateUserInfo(u)){
			map.put("code", 1);
			map.put("msg", "信息已更新");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "更新失败,请检查您的信息是否正确有效");
		return map;
	}
	// 比对用户是否存在，或用户名是否存在
	@RequestMapping("base/checkUsername")
	@ResponseBody
	public Map<String, String> checkUsername(String username) {
		if (userService.checkUserExit(username)) {// 存在
			result = "no";
		} else {
			result = "yes";
		}
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
	//根据用户token取出某些用户信息
	@RequestMapping("base/userInfo")
	@ResponseBody
    public Map<String,Object> tokenInfo(HttpServletRequest request){
		Map<String,Object>map=new HashMap<String,Object>();
		String token = request.getHeader("Authorization");
		if( !JwtUtil.verifyToken(token)){
			map.put("code", 3);
			map.put("msg", "无效的token信息");
			return map;
		}
		map.put("code", 1);
		String username = (String) JwtUtil.parseToken(token).get("username");
		String image = (String) JwtUtil.parseToken(token).get("image");
		String userid=(String) JwtUtil.parseToken(token).get("userid");
		String signature=(String) JwtUtil.parseToken(token).get("signature");
		Integer roleid=(Integer) JwtUtil.parseToken(token).get("roleid");
		map.put("userid", userid);
		map.put("username", username);
		map.put("image", image);
		map.put("signature", signature);
		map.put("roleid", roleid);
		return map;
		
	}
	//去往修改信息页面
	@RequestMapping("user/toEditMe")
	public String toEditMe(Model model, @RequestParam(required=true) String uname, @RequestParam(required=true) String uid, @RequestParam(required=true)String info){
		if(!JwtUtil.verifyToken(info)){
			result="错误的请求,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		String uid1 = (String) JwtUtil.parseToken(info).get("userid");
		if(uid1.equals(uid)){
			User editU = userService.findUserById(uid1);
			model.addAttribute("user", editU);
			return "user/editInfo";
		}
		result="未知错误";
		model.addAttribute("result", result);
		return "error";
	}
	//用户的Forum页面
	@RequestMapping("user/myHome")
	public String toHome(@RequestParam(required=true)String uid, @RequestParam(required=true)String info, Model model){
		if(!JwtUtil.verifyToken(info)){
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		String uid1=(String) JwtUtil.parseToken(info).get("userid");
		if(!uid1.equals(uid)){
			result="未知错误";
			model.addAttribute("result", result);
			return "error";
		}
		Map<String,Object>map=new HashMap<String,Object>();
		String uu=null;
		map=userService.getHomeInfo(uid,uu);
		if(null==map)
			return "error";
		User u = (User) map.get("user");
		List<HomeForum>list = (List<HomeForum>) map.get("list");
		model.addAttribute("user", u);
		model.addAttribute("list", list);
		return "user/myHome";
	}
	//用户的comment
	@RequestMapping("user/myComment")
	public String toMyComment(@RequestParam(required=true)String uid, @RequestParam(required=true)String info, Model model){
		if(!JwtUtil.verifyToken(info)){
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		String uid1=(String) JwtUtil.parseToken(info).get("userid");
		if(!uid1.equals(uid)){
			result="未知错误";
			model.addAttribute("result", result);
			return "error";
		}
		 User commU = userService.findUserById(uid);
		 if(null==commU)
			 return "error";
		 List<UserCommentInfo> list = commentService.findCommentListByUserId(uid, 1, 6);
		 model.addAttribute("user", commU);
		 model.addAttribute("list", list);
		 return "user/myComment";
		
	}
	//查看其他用户的信息
	@RequestMapping("user/otherUser")
	public String toOtherHome(@RequestParam(required=true)String uid, @RequestParam(required=true)String info, Model model){
		if(!JwtUtil.verifyToken(info)){
			result="验证信息过期,请尝试重新登陆重试";
			model.addAttribute("result", result);
			return "error";
		}
		if(null==uid){
			return "error";
		}
		OtherUserInfo oui=userService.findOtherUserById(uid);
		if(null==oui){
			result="用户不存在或者已删除";
			model.addAttribute("result", result);
			return "error";
		}
		model.addAttribute("userInfo", oui);
		return "user/otherUser";
	}
	//用户上传头像
	@RequestMapping( "user/editImg")
	@ResponseBody
    public Map<String,Object> userEditImg(@RequestParam(required=true)String info, HttpServletRequest request , MultipartFile pictureFile){
		 HashMap<String,Object>map=new HashMap<String,Object>();
		if(!JwtUtil.verifyToken(info)||null==pictureFile) {
			map.put("code", 3);
			map.put("msg", "错误的用户");
			return map;
		}
		String userid=(String) JwtUtil.parseToken(info).get("userid");
		String fileName=pictureFile.getOriginalFilename();
		int dotIndex = fileName.indexOf('.');
		//判断文件第一个.后是否还有.如有则为错误的文件
		String substring = fileName.substring(dotIndex+1);
		if(substring.contains(".")){
			map.put("code", 3);
			map.put("msg", "错误的文件");
			return map;
		}
		User editu=new User();
		editu.setUser_id(userid);
		map.clear();
		if(substring.equals("jpg")||substring.equals("png")||substring.equals("jpeg")){
			fileName=userid+new Date().getTime()+"."+substring;
			substring=request.getSession().getServletContext().getRealPath("/images")+"\\"+fileName;
			try {
				pictureFile.transferTo(new File(substring));
				editu.setImage(fileName);
				
				
			} catch (Exception e) {
				map.put("code", 3);
				map.put("msg", "上传失败,请重新选择文件,并按要求上传");
				return map;
			}  
		}
		if(userService.updateUserImage(editu)) {
			map.put("code", 1);
			map.put("msg", "上传成功");
			map.put("data", fileName);
			return map;
		}
		map.put("code", 2);
		map.put("msg", "没有找到合适的对象,上传失败");
		return map;
	}

	@RequestMapping("manager/backUser")
	public String backUserList(HttpServletRequest request, Model model) {
	
		
		PageInfo pi=new PageInfo();
		pi.setCurrentpage(1);
		pi.setPagesize(8);
		
		PageInfo pageinfo=userService.getPageInfo(pi);
		
		List<OtherUserInfo> list=userService.getUserList(pageinfo);
		model.addAttribute("pageInfo", pageinfo);
		model.addAttribute("list", list);
		return "back/backuser";
	}
	@RequestMapping("logout")
	public void logout() {
		System.out.println("logout");
	}
	@RequestMapping("user/chat")
	public String userChart(@RequestParam(required=true)String uid, @RequestParam(required=true)String info, Model model) {
		String result="";
		if(!JwtUtil.verifyToken(info)){
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		String uid1=(String) JwtUtil.parseToken(info).get("userid");
		if(!uid1.equals(uid)){
			result="错误的用户信息";
			model.addAttribute("result", result);
			return "error";
		}
		return "user/chat";
	}
	@RequestMapping("/")
	public String ind() {
		return "redirect:/html/index.html";
	}
	//用户验证学校用户身份
	@RequestMapping("user/authentic")
	public String schoolAuthentic(@RequestParam(required=true)String info, Model model, SchoolUser su) {
		if(!JwtUtil.verifyToken(info)) {
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		if(null==su|| StringUtils.isBlank(su.getStunumber())|| StringUtils.isBlank(su.getUsername())) {
			result="输入信息有误";
			model.addAttribute("result", result);
			return "error";
		}
		String userid=(String) JwtUtil.parseToken(info).get("userid");
		
		if(userService.authenticUser(su)) {
			User u=new User();
			u.setUser_id(userid);
			u.setRole_id(11012);
			userService.updateUserInfo(u);
		}
		return "user/authenticted";
	}
	@RequestMapping("school/index")
	public String toschool(@RequestParam(required=true)String info, Model model) {
		if(null==info) {
			return "error";
		}
		if(!JwtUtil.verifyToken(info)) {
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		List<FeedBack> fblist = fbService.getNearFeedBackList();
		List<ForumInfo> flist1 = forumService.getMomentForum(1, 4);
		List<ForumInfo> flist2 = forumService.getMomentForum(2, 4);
		List<Essay> elist= essayService.findSomeEssay();
		model.addAttribute("flist1", flist1);
		model.addAttribute("flist2", flist2);
		model.addAttribute("elist", elist);
		model.addAttribute("fblist", fblist);
		model.addAttribute("info", info);
		return "school/xiaonei";
	}
	


	
}
