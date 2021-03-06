package com.dabai.dao.Eassy;


import com.dabai.dto.Eassy.Essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  EssayMapper
 */

public interface EssayMapper {

    int addEssay(Essay essay);

    Essay findById(Long id);

    List<Essay> findSomeEssay();
}
