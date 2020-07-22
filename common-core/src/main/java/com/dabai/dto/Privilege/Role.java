package com.dabai.dto.Privilege;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  角色实体
*/
public class Role implements Serializable{

	private static final long serialVersionUID = -233253723716092933L;
	private Integer role_id;
	private String role_name;
	private Integer privilege_percent;
	private boolean isactive;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date last_edit;
	 
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public Integer getPrivilege_percent() {
		return privilege_percent;
	}
	public void setPrivilege_percent(Integer privilege_percent) {
		this.privilege_percent = privilege_percent;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_edit() {
		return last_edit;
	}
	public void setLast_edit(Date last_edit) {
		this.last_edit = last_edit;
	}
	public Role() {
	}
	

}
