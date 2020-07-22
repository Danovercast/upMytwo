package com.dabai.dto.Institution;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  包装institution信息类
*/
public class InstitutionInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long institutionid;
	private String institutionname;
	private String image;
	private String blgimage1;
	private String blgimage2;
	private String blgimage3;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createtime;
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Long getInstitutionid() {
		return institutionid;
	}
	public void setInstitutionid(Long institutionid) {
		this.institutionid = institutionid;
	}
	public String getInstitutionname() {
		return institutionname;
	}
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBlgimage1() {
		return blgimage1;
	}
	public void setBlgimage1(String blgimage1) {
		this.blgimage1 = blgimage1;
	}
	public String getBlgimage2() {
		return blgimage2;
	}
	public void setBlgimage2(String blgimage2) {
		this.blgimage2 = blgimage2;
	}
	public String getBlgimage3() {
		return blgimage3;
	}
	public void setBlgimage3(String blgimage3) {
		this.blgimage3 = blgimage3;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

	
}
