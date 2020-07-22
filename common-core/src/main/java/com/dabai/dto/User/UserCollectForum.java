package com.dabai.dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  用户收藏帖子
*/
public class UserCollectForum implements Serializable {

	private static final long serialVersionUID = -4574088258293050859L;
	private Long forum_id;
	private String user_id;
	private Boolean is_collect;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;
 
	public Long getForum_id() {
		return forum_id;
	}
	public void setForum_id(Long forum_id) {
		this.forum_id = forum_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Boolean getIs_collect() {
		return is_collect;
	}
	public void setIs_collect(Boolean is_collect) {
		this.is_collect = is_collect;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public UserCollectForum() {
	}
	

}
