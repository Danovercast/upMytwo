package com.dabai.dto.SomeInfo;

import java.io.Serializable;

/**  
* @author dabai: 

* 类说明  包装多个id  数组id string  Integer类型
*/
public class SomeIds implements Serializable {

	private static final long serialVersionUID = 769776315024849516L;
	private Long forumid;
	private String userid;
	private Long institutionid;
	private Integer roleid;
	private Integer privilegeid;
	private Long[]institutionids;
	private Integer[]intergerids;
	private String[]Stringids;
	public Long getForumid() {
		return forumid;
	}
	public void setForumid(Long forumid) {
		this.forumid = forumid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Long getInstitutionid() {
		return institutionid;
	}
	public void setInstitutionid(Long institutionid) {
		this.institutionid = institutionid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getPrivilegeid() {
		return privilegeid;
	}
	public void setPrivilegeid(Integer privilegeid) {
		this.privilegeid = privilegeid;
	}
	public Long[] getInstitutionids() {
		return institutionids;
	}
	public void setInstitutionids(Long[] institutionids) {
		this.institutionids = institutionids;
	}
	public Integer[] getIntergerids() {
		return intergerids;
	}
	public void setIntergerids(Integer[] intergerids) {
		this.intergerids = intergerids;
	}
	public String[] getStringids() {
		return Stringids;
	}
	public void setStringids(String[] stringids) {
		Stringids = stringids;
	}
	
	

}
