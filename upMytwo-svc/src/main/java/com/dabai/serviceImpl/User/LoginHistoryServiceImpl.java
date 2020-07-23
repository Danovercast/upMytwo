package com.dabai.serviceImpl.User;

import api.User.LoginHistoryService;
import com.dabai.dao.User.LoginHistoryMapper;
import com.dabai.dto.SomeInfo.LoginHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashMap;

/**
 * @author dabai:
 * <p>
 * 类说明  LoginHistory实现类
 */
@DubboService
public class LoginHistoryServiceImpl implements LoginHistoryService {

    @Autowired
    private LoginHistoryMapper loginHistoryMapper;

    @Override
    public Boolean addLonginHistory(LoginHistory loginHistory) {
        return loginHistoryMapper.insertLoginHistory(loginHistory) == 1;

    }

    @Override
    public Boolean deleteLoginHistoryById(LoginHistory loginHistory) {
        return loginHistoryMapper.deleteLoginHistoryById(loginHistory) == 1;

    }

    @Override
    public HashMap<String, Object> findUserLoginHistory(String user_id, Integer currentPage, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Integer totalCount = loginHistoryMapper.getCountByUser(user_id);
        Integer totalPage = (int) Math.ceil(totalCount / pageSize + 0.4);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        map.put("totalCount", totalCount);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("list", loginHistoryMapper.findUserLoginHistory(user_id, (currentPage - 1) * pageSize, pageSize));
        return map;
    }

}
