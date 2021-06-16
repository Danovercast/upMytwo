package com.dabai.dao.Institution;


import com.dabai.dto.Institution.Institution;
import com.dabai.dto.Institution.InstitutionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface InstitutionMapper {
    Integer insertInstitution(Institution institution);

    int updateInstitution(Institution institution);

    List<Institution> findAllInstitution();

    Institution findById(Long institution_id);

    int getCountInstitution();

    List<Institution> findInstitutionList(@Param("i")Integer i,@Param("pagesize") Integer pagesize);

    List<InstitutionInfo> findInstitutionInfo(@Param("start")Integer start,@Param("end") Integer end);

    InstitutionInfo getInstitutionInfoById(Long insid);

    // List<InstitutionInfo> findInstitutionInfoList(Integer start, Integer pagesize);
}