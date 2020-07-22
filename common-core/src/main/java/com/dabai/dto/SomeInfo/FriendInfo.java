package com.dabai.dto.SomeInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  包装用户好友申请信息
*/
@Data
@NoArgsConstructor
public class FriendInfo implements Serializable{

	private static final long serialVersionUID = -7291149133824534261L;
	private String id;
	private String userid;
	private Boolean isfriend;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createtime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date admittime;
	private String username;
	private Integer gender;
	private String signature;
	private String request;
	private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Boolean getIsfriend() {
		return isfriend;
	}
	public void setIsfriend(Boolean isfriend) {
		this.isfriend = isfriend;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getAdmittime() {
		return admittime;
	}
	public void setAdmittime(Date admittime) {
		this.admittime = admittime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	

}
