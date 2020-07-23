package com.dabai.dao.Comment;


import com.dabai.dto.SomeInfo.FeedBack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明 FeedBackMapper
 */
@Mapper
public interface FeedBackMapper {

    List<FeedBack> findFeedBackList(String userid, Integer start);

    FeedBack findById(Integer id);

    List<FeedBack> getNearFeedBackList();

    Integer addFeedBack(FeedBack fb);

}
