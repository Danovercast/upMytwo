package com.dabai.dto.Comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  评论实体类
*/
@Data
@NoArgsConstructor
@ToString
public class Comment implements Serializable {

	private static final long serialVersionUID = -8616805594523386756L;
	private String comment_id;
	private Long forum_id;
	private String responser_id;
	private String details;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date create_time;
	private Integer state;


}
