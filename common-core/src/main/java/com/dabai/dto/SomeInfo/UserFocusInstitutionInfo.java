package com.dabai.dto.SomeInfo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  包装信息类
*/
public class UserFocusInstitutionInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long institution_id;
	private String institution_name;
	private String description;
	private String image;
	private String blg_image1;
	private String blg_image2;
	private String blg_image3;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date focus_start;
	private Integer totalPage;
	private Integer totalCount;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBlg_image1() {
		return blg_image1;
	}
	public void setBlg_image1(String blg_image1) {
		this.blg_image1 = blg_image1;
	}
	public String getBlg_image2() {
		return blg_image2;
	}
	public void setBlg_image2(String blg_image2) {
		this.blg_image2 = blg_image2;
	}
	public String getBlg_image3() {
		return blg_image3;
	}
	public void setBlg_image3(String blg_image3) {
		this.blg_image3 = blg_image3;
	}
	public Date getFocus_start() {
		return focus_start;
	}
	public void setFocus_start(Date focus_start) {
		this.focus_start = focus_start;
	}
	
	

}
