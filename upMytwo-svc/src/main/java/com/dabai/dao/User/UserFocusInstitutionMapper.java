package com.dabai.dao.User;


import com.dabai.dto.SomeInfo.UserFocusInstitution;
import com.dabai.dto.SomeInfo.UserFocusInstitutionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserFocusInstitutionMapper {
    public int insertUserFocusInstitution(UserFocusInstitution userFocusInstitution);

    public int updateByUserIdInstitutionId(UserFocusInstitution userFocusInstitution);

    public List<UserFocusInstitutionInfo> findUserFocusInstitutionContentByUserId(String user_id, Integer i, Integer pageSize);

    public Integer getCountFocusInstitution(String user_id);

    public List<UserFocusInstitutionInfo> findUserFocusInstitutionContentByUserId(Map<String, Object> map);

}