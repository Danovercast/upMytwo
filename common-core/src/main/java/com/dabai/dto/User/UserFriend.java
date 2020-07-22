package com.dabai.dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  用户是否朋友
*/
public class UserFriend implements Serializable {

	 
	private static final long serialVersionUID = 6512386893080674640L;
	private String id;
	private String user1_id;
	private String user2_id;
	private Boolean is_friend;
	private Boolean ignorethis;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date admit_time;
	private String request;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public Date getAdmit_time() {
		return admit_time;
	}
	public void setAdmit_time(Date admit_time) {
		this.admit_time = admit_time;
	}
	public Boolean getIgnorethis() {
		return ignorethis;
	}
	public void setIgnorethis(Boolean ignorethis) {
		this.ignorethis = ignorethis;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 
	public String getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(String user1_id) {
		this.user1_id = user1_id;
	}
	public String getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(String user2_id) {
		this.user2_id = user2_id;
	}
	public Boolean getIs_friend() {
		return is_friend;
	}
	public void setIs_friend(Boolean is_friend) {
		this.is_friend = is_friend;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public UserFriend() {
	}
	

}
