package com.dabai.dto.User;

import java.io.Serializable;

/**  
* @author dabai: 

* 类说明  用户身份类
*/
public class Identity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String identityname;
	private String image;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentityname() {
		return identityname;
	}
	public void setIdentityname(String identityname) {
		this.identityname = identityname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
