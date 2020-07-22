package com.dabai.dto.SomeInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  提交的反馈内容
*/
@Data
@NoArgsConstructor
public class FeedBack implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userid;
	private String stunumber;
	private String picture;
	private String content;
	private Date time;
	private String category;

}
