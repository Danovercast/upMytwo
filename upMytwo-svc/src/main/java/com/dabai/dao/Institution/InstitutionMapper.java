package com.dabai.dao.Institution;


import com.dabai.dto.Institution.Institution;
import com.dabai.dto.Institution.InstitutionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstitutionMapper {
    Integer insertInstitution(Institution institution);

    int updateInstitution(Institution institution);

    List<Institution> findAllInstitution();

    Institution findById(Long institution_id);

    int getCountInstitution();

    List<Institution> findInstitutionList(Integer i, Integer pagesize);

    List<InstitutionInfo> findInstitutionInfo(Integer start, Integer end);

    InstitutionInfo getInstitutionInfoById(Long insid);

    // List<InstitutionInfo> findInstitutionInfoList(Integer start, Integer pagesize);
}