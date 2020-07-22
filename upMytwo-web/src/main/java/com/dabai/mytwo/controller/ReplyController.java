package com.dabai.mytwo.controller;
/**  
* @author dabai: 

* 类说明  ReplyController
*/

import org.apache.dubbo.config.annotation.DubboReference;
import com.dabai.dto.Comment.Reply;
import api.Comment.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Controller
public class ReplyController {
	private String result="";
	@DubboReference
	private ReplyService replyService;
	
	@RequestMapping("manager/reply")
	public HashMap<String,Object> replyFeedBack(Reply reply) {
		HashMap<String,Object>map=new HashMap<String,Object>();
		if(null==reply||null==reply.getFbid()) {
			map.put("code", 3);
			map.put("msg", "错误的信息");
			return map;
		}
		reply.setRid(UUID.randomUUID().toString().replaceAll("-", ""));
		reply.setTime(new Date());
		if(replyService.addReply(reply)) {
			map.put("code", 1);
			map.put("msg", "已发送");
			return map;
		}
		map.put("code", 2);
		map.put("msg", "发送失败");
		return map;
	}

}
