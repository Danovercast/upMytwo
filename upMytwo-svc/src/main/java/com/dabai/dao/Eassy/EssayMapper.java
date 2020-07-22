package com.dabai.dao.Eassy;


import com.dabai.dto.Eassy.Essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**  
* @author dabai: 

* 类说明  EssayMapper
*/
@Mapper
public interface EssayMapper {

	int addEssay(Essay essay);

	Essay findById(Long id);

	List<Essay> findSomeEssay();
}
