package com.dabai.dto.Institution;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  学院类
*/
public class Institution implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Long institution_id;
	private String institution_name;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;
	public Long getInstitution_id() {
		return institution_id;
	}
	public void setInstitution_id(Long institution_id) {
		this.institution_id = institution_id;
	}
	public String getInstitution_name() {
		return institution_name;
	}
	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
 
	

}
