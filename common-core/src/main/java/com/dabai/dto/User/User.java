package com.dabai.dto.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  用户实体类
*/
@ApiModel(value="用户对象信息")
public class User implements Serializable {

	private static final long serialVersionUID = -8971569712457440539L;
	@ApiModelProperty(value="user_id" ,required=true)
	private String user_id;
	@ApiModelProperty(value="角色id" ,required=true)
	private Integer role_id;
	@ApiModelProperty(value="学院id" ,required=true)
	private Long institution_id;
	@ApiModelProperty(value="用户名" ,required=true)
	private String username;
	@ApiModelProperty(value="密码" ,required=true)
	private String password;
	@ApiModelProperty(value="邮箱地址" ,required=true)
	private String email;
	@ApiModelProperty(value="手机号" ,required=true)
	private String telephone;
	@ApiModelProperty(value="生日" ,required=true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@ApiModelProperty(value="头像" ,required=true)
	private String image;
	@ApiModelProperty(value="性别" ,required=true)
	private Integer gender;
	@ApiModelProperty(value="个人签名" ,required=true)
	private String signature;
	private String activecode;
	@ApiModelProperty(value="注册日期" ,required=true)
	private Date register;
	
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	 
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Long getInstitution_id() {
		return institution_id;
	}
	public void setInstitution_id(Long institution_id) {
		this.institution_id = institution_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	 
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public User() {
	}
	

}
