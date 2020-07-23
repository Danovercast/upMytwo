package com.dabai.dao.Institution;


import com.dabai.dto.Institution.InstitutionContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InstitutionContentMapper {
    public int insertInstitutionContent(InstitutionContent institutionContent);

    public int updateInstitutionContent(InstitutionContent institutionContent);

    public InstitutionContent findInstitutionContentByInstitutionId(long institution_id);

    public List<InstitutionContent> findInstitutionContentListByInstitutionIds(Long[] institution_ids);

}