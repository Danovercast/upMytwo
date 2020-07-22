package com.dabai.dto.Privilege;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  角色和权限关联表
*/
public class RolePrivilege implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer role_id;
	private Integer privilege_id;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date last_edit;
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(Integer privilege_id) {
		this.privilege_id = privilege_id;
	}
	public Date getLast_edit() {
		return last_edit;
	}
	public void setLast_edit(Date last_edit) {
		this.last_edit = last_edit;
	}
	public RolePrivilege() {
	}
	

}
