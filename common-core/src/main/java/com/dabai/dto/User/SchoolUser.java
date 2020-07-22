package com.dabai.dto.User;

import java.io.Serializable;

/**  
* @author dabai: 

* 类说明  学院用户信息
*/
public class SchoolUser implements Serializable {

	private static final long serialVersionUID = 1L;
	String id;
	String stunumber;
	String username;
	Long institutionid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStunumber() {
		return stunumber;
	}
	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getInstitutionid() {
		return institutionid;
	}
	public void setInstitutionid(Long institutionid) {
		this.institutionid = institutionid;
	} 

}
