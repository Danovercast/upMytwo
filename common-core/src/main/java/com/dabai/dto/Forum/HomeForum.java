package com.dabai.dto.Forum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  包装首次显示首页的信息
*/
@Data
@NoArgsConstructor
public class HomeForum implements Serializable {

	private static final long serialVersionUID = 1L;
	private String insimg;
	private Long forumid;
	private Long insid;
	private String title;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date createtime;
	private String insname;

}
