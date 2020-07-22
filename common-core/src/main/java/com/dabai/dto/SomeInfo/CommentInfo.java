package com.dabai.dto.SomeInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  封装forum页面显示Comment的一些信息
*/
@Data
@NoArgsConstructor
@ToString
public class CommentInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String commentid;
	private String responserid;
	private String details;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createtime;
	private String responsername;
	private String image;
	private Integer gender;
	private String signature;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date register;

	

}
