package com.dabai.dto.Privilege;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**  
* @author dabai: 

* 类说明  权限实体
*/
public class Privilege implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer privilege_id;
	private String privilege_name;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date last_edit;
	private List<RolePrivilege>role_privileges;
	public List<RolePrivilege> getRole_privileges() {
		return role_privileges;
	}
	public void setRole_privileges(List<RolePrivilege> role_privileges) {
		this.role_privileges = role_privileges;
	}
	public Integer getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(Integer privilege_id) {
		this.privilege_id = privilege_id;
	}
	public String getPrivilege_name() {
		return privilege_name;
	}
	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
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
	public Privilege() {
	}
	

}
