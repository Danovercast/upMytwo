package com.dabai.dao.Comment;


import com.dabai.dto.SomeInfo.FeedBack;

import java.util.List;

/**  
* @author dabai: 

* 类说明 FeedBackMapper
*/
public interface FeedBackMapper {

	List<FeedBack> findFeedBackList(String userid, Integer start);

	FeedBack findById(Integer id);

	List<FeedBack> getNearFeedBackList();

	Integer addFeedBack(FeedBack fb);

}
