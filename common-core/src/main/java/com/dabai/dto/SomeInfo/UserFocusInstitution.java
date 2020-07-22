package com.dabai.dto.SomeInfo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  用户关注某个学院
*/
public class UserFocusInstitution implements Serializable {

	private static final long serialVersionUID = -6351371441608720673L;
	private String id;
	private Long institution_id;
	private String user_id;
	private boolean is_focus;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date focus_start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date focus_end;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	public Long getInstitution_id() {
		return institution_id;
	}
	public void setInstitution_id(Long institution_id) {
		this.institution_id = institution_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isIs_focus() {
		return is_focus;
	}
	public void setIs_focus(boolean is_focus) {
		this.is_focus = is_focus;
	}
	public Date getFocus_start() {
		return focus_start;
	}
	public void setFocus_start(Date focus_start) {
		this.focus_start = focus_start;
	}
	public Date getFocus_end() {
		return focus_end;
	}
	public void setFocus_end(Date focus_end) {
		this.focus_end = focus_end;
	}
	public UserFocusInstitution() {
	}
	

}
