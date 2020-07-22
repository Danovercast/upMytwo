package com.dabai.dao.Comment;


import com.dabai.dto.Comment.Reply;
import org.apache.ibatis.annotations.Mapper;

/**
* @author dabai: 

* 类说明  ReplyMapper
*/
@Mapper
public interface ReplyMapper {

	int addReply(Reply reply);

}
