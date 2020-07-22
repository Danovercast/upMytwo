package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.FeedBack;
import api.Comment.FeedBackService;
import com.dabai.mytwo.util.JwtUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**  
* @author dabai: 

* 类说明  
*/
@Controller
public class FeedBackController {
	private String result = "";
	@DubboReference
	private FeedBackService feedBackService;
	
	@RequestMapping("feedBack/myFeedBack")
	public String myFeedBack(Model model, @RequestParam(required=false) Integer currentpage, @RequestParam(required=true)String info){
		if(!JwtUtil.verifyToken(info)){
			result="如身份过期,请登陆后重试";
			model.addAttribute("result", result);
			return "error";
		}
		if(null==currentpage||currentpage<1)
			currentpage=1;
		String userid = (String) JwtUtil.parseToken(info).get("userid");
		List<FeedBack>list=feedBackService.findMyFeedBack(userid,currentpage);
		model.addAttribute("list", list);
		return "school/wodefankui";
	}
	@RequestMapping("f/{id}")
	public String seeFeedBack(Model model, @PathVariable Integer id){
		FeedBack fb=feedBackService.findFeedBackById(id);
		if(null!=fb) {
			model.addAttribute("feedback", fb);
			return "feedbackDetails";
		}
		result="请求资源不存在";
		model.addAttribute("result", result);
		return "error";
	}
	@RequestMapping("feedback/toFeedback")
	public String toFeedBack(Model model){
		List<FeedBack> fblist = feedBackService.getNearFeedBackList();
		model.addAttribute("fblist", fblist);
		return "school/tichufankui";
		
	}
	@RequestMapping("feedback/toSendFeedBack")
	public String toSendFeedBack(Model model, @RequestParam(required=true)String info){
		if(JwtUtil.verifyToken(info)) {
			model.addAttribute("info", info);
			String image = (String) JwtUtil.parseToken(info).get("image");
			String username = (String) JwtUtil.parseToken(info).get("username");
			String userid = (String) JwtUtil.parseToken(info).get("userid");
			model.addAttribute("image", image);
			model.addAttribute("username", username);
			model.addAttribute("userid", userid);
			return "school/xinjianfankui";
		}
		result="验证信息错误,请重新登陆";
		return "error";
	}
	@RequestMapping("feedBack/send")
		public String sendFeedBack(Model model, FeedBack fb){
		HashMap<String,Object> map=new HashMap<String,Object> ();
		if(null==fb||fb.getUserid()==null) {
			result="错误的信息";
			return "error";
		}
		fb.setTime(new Date());
		Integer fbid=feedBackService.addFeedBack(fb);
		if(null==fbid) {
			result="添加失败,请检查是否正确操作";
			return "error";
		}
		model.addAttribute("feedback", fb);
		return "school/advicedetails";
	}
		
}
