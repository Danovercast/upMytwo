package com.dabai.mytwo.controller;

import com.dabai.dto.Eassy.Essay;
import api.Eassy.EssayService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**  
* @author dabai: 

* 类说明  EssayController
*/
@Controller
public class EssayController {
	private String result="";
	
	@DubboReference
	private EssayService essayService;
	
	@RequestMapping("jwt/sendEssay")
	public String sendEssay(Model model, Essay essay, HttpServletRequest request){
		if(null==essay|| StringUtils.isBlank(essay.getContent())) {
			result="发送失败,请确保信息正确填写";
			return "error";
		}
		if(essayService.addEssay(essay)){
			model.addAttribute("essay", essay);
			return "e/essay";
		}
		result="发送失败,未知错误";
		return "error";
	}
	
	@RequestMapping("e/{id}")
	public String toEssay(Model model, @PathVariable("id") Long id){
		Essay essay=essayService.findById(id);
		if(essay!=null) {
		model.addAttribute("essay", essay);
		return "e/essay";
		}
		result="请求资源不存在";
		return "error";
	}
	@RequestMapping("base/loadEssay")
	@ResponseBody
	public List<Essay> someEssay(Model model){
		List<Essay>list=essayService.findSomeEssay();
		return list;
	}
}
