package com.dabai.serviceImpl.Comment;


import com.dabai.dto.SomeInfo.FeedBack;
import api.Comment.FeedBackService;
import com.dabai.dao.Comment.FeedBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.util.List;

/**  
* @author dabai: 

* 类说明  FeedBackService实现类
*/
@DubboService
@Component
public class FeedBackServiceImpl implements FeedBackService {
	@Autowired
	private FeedBackMapper fbMapper;

	
	@Override
	public List<FeedBack> findMyFeedBack(String userid, Integer currentpage) {
		return fbMapper.findFeedBackList(userid,(currentpage-1)*6);
	}

	@Override
	public FeedBack findFeedBackById(Integer id) {
		 
		return fbMapper.findById(id);
	}

	@Override
	public List<FeedBack> getNearFeedBackList() {
		return fbMapper.getNearFeedBackList();
	}

	@Override
	public Integer addFeedBack(FeedBack fb) {
		return fbMapper.addFeedBack(fb);
	}

}
