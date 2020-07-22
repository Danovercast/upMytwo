package api.Eassy;

import com.dabai.dto.Eassy.Essay;

import java.util.List;

/**  
* @author dabai: 

* 类说明  EssayService
*/
public interface EssayService {

	boolean addEssay(Essay essay);

	Essay findById(Long id);

	List<Essay> findSomeEssay();

}
