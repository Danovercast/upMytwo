package com.dabai.dao.User;


import com.dabai.dto.SomeInfo.LoginHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {
    public int insertLoginHistory(LoginHistory loginHistory);

    public int deleteLoginHistoryById(LoginHistory loginHistory);

    public List<LoginHistory> findUserLoginHistory(String user_id, Integer start, Integer end);

    public Integer getCountByUser(String user_id);
}