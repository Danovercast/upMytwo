package api.Comment;



import com.dabai.dto.SomeInfo.FeedBack;

import java.util.List;

/**  
* @author dabai: 

* 类说明  反馈接口
*/
public interface FeedBackService {

	List<FeedBack> findMyFeedBack(String userid, Integer currentpage);

	FeedBack findFeedBackById(Integer id);
	
	List<FeedBack> getNearFeedBackList();

	Integer addFeedBack(FeedBack fb);
}
