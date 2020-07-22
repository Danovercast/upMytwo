package com.dabai.dao.User;



import com.dabai.dto.SomeInfo.LoginHistory;

import java.util.List;

public interface LoginHistoryMapper {
	public int insertLoginHistory(LoginHistory loginHistory);
	public int deleteLoginHistoryById(LoginHistory loginHistory);
	public List<LoginHistory> findUserLoginHistory(String user_id, Integer start, Integer end);
	public Integer getCountByUser(String user_id);
}