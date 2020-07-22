package com.dabai.dto.Comment;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  用户反馈的回复
*/
public class Reply implements Serializable {

	private static final long serialVersionUID = 1L;
	Integer fbid;
	String rid;
	Date time;
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFbid() {
		return fbid;
	}
	public void setFbid(Integer fbid) {
		this.fbid = fbid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	

}
