package com.dabai.dto.SomeInfo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  封装用户发布comment的一些显示信息
*/
public class UserCommentInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String comid;
	private Long forumid;
	private String uid;
	private String details;
	private String title;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createtime;
	private String uname;
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public Long getForumid() {
		return forumid;
	}
	public void setForumid(Long forumid) {
		this.forumid = forumid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	

}
