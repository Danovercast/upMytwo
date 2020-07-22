package com.dabai.dto.SomeInfo;

import java.io.Serializable;

/**  
* @author dabai: 

* 类说明  封装分页的页数信息
*/
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer totalcount;
	private Integer currentpage;
	private Integer totalpage;
	private Integer pagesize;
	private Long institutionid;
	private Long forumid;
	private String userid;
	private String ids;
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public Integer getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Long getInstitutionid() {
		return institutionid;
	}
	public void setInstitutionid(Long institutionid) {
		this.institutionid = institutionid;
	}
	public Long getForumid() {
		return forumid;
	}
	public void setForumid(Long forumid) {
		this.forumid = forumid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
 
	

}
