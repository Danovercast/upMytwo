package com.dabai.mytwo.controller;

import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.Institution.Institution;
import com.dabai.dto.Institution.InstitutionInfo;
import api.Institution.InstitutionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author dabai:
 * 
 *         类说明 institution前端控制器
 */
@Controller
public class InstitutionController {

	@DubboReference
	private InstitutionService institutionService;
	private String result = "";

	// 添加institution信息
	@RequestMapping("manager/addInstitution")
	@ResponseBody
	HashMap<String, Object> addInstitution(@RequestBody Institution institution) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (null == institution || StringUtils.isBlank(institution.getInstitution_name())) {
			map.put("code", 3);
			map.put("msg", "学院名称不能为空");
			return map;
		}
		institution.setCreate_time(new Date());
		if (institutionService.addInstitution(institution)) {
			map.put("code", 1);
			map.put("msg", "创建成功");
			map.put("data", "学院              (" + institution.getInstitution_name() + ")     创建成功" + "学院id"
					+ institution.getInstitution_id());
			return map;
		}
		map.put("code", 2);
		map.put("msg", "学院未创建,请确定institution的名字唯一,或者q请检查权限和网络");
		return map;
	}

	// 更新institution字段
	@RequestMapping("manager/editInstitution")
	public String updateInstitution(@RequestBody Institution institution, Model model) {
		if (null == institution || null == institution.getInstitution_id()) {
			result = "异常错误";
			model.addAttribute("result", result);
			return "error";
		}
		if (institutionService.updateInstitutionById(institution)) {
			result = "更新成功";
			model.addAttribute("result", result);
			return "back/backins";
		}
		result="学院名不可重复";
		model.addAttribute("result", result);
		return "error";
	}

	// 查询回显某些institution的信息
	@RequestMapping("base/institutionPageInfo")
	@ResponseBody
	public PageInfo getPageInfo(@RequestBody PageInfo pageInfo) {
		Integer currentPage = 1, pageSize = 6;
		if (null == pageInfo) {
			pageInfo = new PageInfo();
			pageInfo.setCurrentpage(currentPage);
			pageInfo.setPagesize(pageSize);
		} else {
			currentPage = pageInfo.getCurrentpage();
			pageSize = pageInfo.getPagesize();
			if (null == currentPage || currentPage < 0)
				pageInfo.setCurrentpage(1);
			if (null == pageSize || pageSize < 1)
				pageInfo.setPagesize(6);
		}
		Integer count = institutionService.getCount();
		pageInfo.setTotalcount(count);
		Integer totalPage = (int) Math.ceil(count / pageInfo.getPagesize() + 0.5);
		if (pageInfo.getCurrentpage() > totalPage)
			pageInfo.setCurrentpage(totalPage);
		pageInfo.setTotalpage(totalPage);
		return pageInfo;
	}

	// 查询institution经过分页的list
	@RequestMapping("base/institutionList")
	@ResponseBody
	List<Institution> getInstitutionList(@RequestBody PageInfo pageInfo) {
		if (null == pageInfo || pageInfo.getPagesize() == null || pageInfo.getPagesize() < 1) {
			pageInfo = new PageInfo();
			pageInfo.setPagesize(12);
			pageInfo.setCurrentpage(1);
		}
		if (pageInfo.getCurrentpage() == null || pageInfo.getCurrentpage() < 1)
			pageInfo.setCurrentpage(1);
		return institutionService.findInstitutionList(pageInfo);
	}
	//查询instituion的内容list
	@RequestMapping("base/institutionInfoList")
	@ResponseBody
	List<InstitutionInfo> institutionInfo(@RequestBody PageInfo pageInfo) {
		if (null == pageInfo || pageInfo.getPagesize() == null || pageInfo.getPagesize() < 1) {
			pageInfo = new PageInfo();
			pageInfo.setPagesize(12);
			pageInfo.setCurrentpage(1);
		}
		if (pageInfo.getCurrentpage() == null || pageInfo.getCurrentpage() < 1)
			pageInfo.setCurrentpage(1);
		
		List<InstitutionInfo>ls=institutionService.findInstitutionInfo(pageInfo);
		return ls;
	}
	@RequestMapping("ins/{insid}")
	public String toInspage(@PathVariable(value="insid")Long insid, Model model){
		if(null==insid||insid<1){
			return "error";
		}
		InstitutionInfo ii=institutionService.getInstitutionInfoById(insid);
		if(null==ii){
			result="请求资源不存在";
			model.addAttribute("result", result);
			return "error";
		}
		model.addAttribute("insInfo", ii);
		return "forum/institution";
		
	}
	@RequestMapping("manager/backIns")
	public String backIns(Model model) {
		PageInfo pi=new PageInfo();
		pi.setCurrentpage(1);
		pi.setPagesize(8);
		List<Institution> list = institutionService.findInstitutionList(pi);
		model.addAttribute("list", list);
		return "back/backins";
	}
	@RequestMapping("manager/backInsContent")
	public String backInsContent(Model model) {
		PageInfo pi=new PageInfo();
		pi.setCurrentpage(1);
		pi.setPagesize(8);
		List<InstitutionInfo> list = institutionService.findInstitutionInfo(pi);
		Integer count = institutionService.getCount();
		Integer totalPage=(int) Math.ceil((count/pi.getPagesize()+0.5));
		pi.setTotalcount(count);
		pi.setTotalpage(totalPage);
		model.addAttribute("pageInfo", pi);
		model.addAttribute("list", list);
		return "back/backinscontent";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
