package com.dabai.dto.Eassy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**  
* @author dabai: 

* 类说明  文章 通知
*/
@Data
@NoArgsConstructor
public class Essay implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date time;
	private String authorname;
	private String content;


}
