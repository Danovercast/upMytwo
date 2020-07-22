package com.dabai.mytwo.controller;

import com.dabai.dto.Institution.InstitutionContent;
import api.Institution.InstitutionContentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**  
* @author dabai: 

* 类说明  InstitutionContent前端控制器
*/
@Controller
public class InstitutionContentController {
	
	@DubboReference
	private InstitutionContentService insConService;
	String result="";
	
	//根据institution id查询
	@RequestMapping("base/institutionContent")
	@ResponseBody
    public InstitutionContent getAInstitutionContent(Long institution_id){
		if(null==institution_id)
			institution_id=1l;
		return insConService.getInstitutionContentByInstitutionId(institution_id);
	}
	//获得指定institutionids的institutionContentList
	@RequestMapping("base/institutionContentList")
	@ResponseBody
    public List<InstitutionContent> getAInstitutionContent(String institution_ids){
		System.out.println(institution_ids);
		if(institution_ids==null){
			institution_ids="1";
		}
		String[] ids = institution_ids.split(",");
		Long[]idss=new Long[ids.length];
		for(int i=0;i<ids.length;i++){
			idss[i]=(long) Integer.parseInt(ids[i]);
		}
		if(null==ids||ids.length<1){
			List<InstitutionContent>ls=new ArrayList();
			return ls;
		}
		System.out.println(institution_ids);

		return insConService.findInstitutionContentListByInstitutionIds(idss);
	}
	
	//修改institutionContent
	@RequestMapping("manager/editInstitutionContent")
	@ResponseBody
    public HashMap<String,Object>editInstitutionContent(@RequestBody InstitutionContent ic ){
		 HashMap<String,Object> map=new  HashMap<String,Object>();
		 if(null==ic||null==ic.getId()||null==ic.getDescription()){
			 map.put("code", 3);
			 map.put("msg", "InstitutionContent 的信息错误");
			 return map;
		 }
		 if(insConService.updateInstitutionContentById(ic)){
			 map.put("code", 1);
			 map.put("msg", "更新成功");
			 map.put("data", "一条InstitutionContent记录被修改");
			 return map;
		 }
		 map.put("code", 2);
		 map.put("msg", "更新失败  可能网络延迟或该记录不存在");
		 return map;
	}
	//添加InstitutionContent记录
	@RequestMapping("manager/addInstitutionContent")
	@ResponseBody
    public HashMap<String,Object>addInstitutionContent(@RequestBody InstitutionContent ic ){
		 HashMap<String,Object> map=new  HashMap<String,Object>();
		 if(null==ic||null==ic.getState()){
			 map.put("code", 3);
			 map.put("msg", "InstitutionContent 的信息错误 确保InstitutionContent启用状态和其他信息不为空");
			 return map;
		 }
		 ic.setId(UUID.randomUUID().toString().replace("-", ""));
		 if(insConService.addInstitutionContent(ic)){
			 map.put("code", 1);
			 map.put("msg", "添加成功");
			 map.put("data", "一条InstitutionContent记录被添加");
			 return map;
		 }
		 map.put("code", 2);
		 map.put("msg", "添加失败  可能网络延迟或该记录不存在");
		 return map;
	}
	

}
