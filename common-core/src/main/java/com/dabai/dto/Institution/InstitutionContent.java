package com.dabai.dto.Institution;

import java.io.Serializable;

/**  
* @author dabai: 

* 类说明  学院的后台编辑信息
*/
public class InstitutionContent implements Serializable {

	private static final long serialVersionUID = 3161630489072870744L;
	private String id;
	private Long institution_id;
	private String description;
	private String image;
	private String blg_image1;
	private String blg_image2;
	private String blg_image3;
	private Boolean state;
	public String getBlg_image3() {
		return blg_image3;
	}
	public void setBlg_image3(String blg_image3) {
		this.blg_image3 = blg_image3;
	}

	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
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
 
	public InstitutionContent() {
	}
	

}
