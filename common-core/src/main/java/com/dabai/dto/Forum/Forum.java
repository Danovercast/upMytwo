package com.dabai.dto.Forum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  帖子实体类
*/
@Data
@NoArgsConstructor
public class Forum implements Serializable {

	private static final long serialVersionUID = 8211813130204035887L;
	private Long forum_id;
	private String author_id;
	private Long institution_id;
	private String title;
	private String description;
	private Integer state;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;

	

}
