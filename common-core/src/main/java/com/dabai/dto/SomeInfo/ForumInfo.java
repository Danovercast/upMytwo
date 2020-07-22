package com.dabai.dto.SomeInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  包装forum的一些其他信息
*/
@Data
@NoArgsConstructor
public class ForumInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long forumid;
	private String authorid;
	private Long institutionid;
	private String title;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createtime;
	private String institutionname;
	private String authorname;
	private String image;
	private String signature;
	private Integer state;

}
