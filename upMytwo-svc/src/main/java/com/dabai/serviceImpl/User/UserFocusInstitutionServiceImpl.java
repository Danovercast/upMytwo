package com.dabai.serviceImpl.User;

import com.dabai.dto.SomeInfo.UserFocusInstitution;
import com.dabai.dto.SomeInfo.UserFocusInstitutionInfo;
import api.User.UserFocusInstitutionService;
import com.dabai.dao.User.UserFocusInstitutionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author dabai:
 * <p>
 * 类说明 UserFocusInstitutionService实现类
 */
@DubboService
public class UserFocusInstitutionServiceImpl implements UserFocusInstitutionService {

    @Autowired
    private UserFocusInstitutionMapper userFocusInstitutionMapper;

    @Override
    public Boolean addUserFocusInstitution(UserFocusInstitution userFocusInstitution) {
        userFocusInstitution.setFocus_start(new Date());
        userFocusInstitution.setId(UUID.randomUUID().toString());
        userFocusInstitution.setIs_focus(true);
        return userFocusInstitutionMapper.insertUserFocusInstitution(userFocusInstitution) == 1;

    }

    @Override
    public Boolean updateByUserIdInstitutionId(UserFocusInstitution userFocusInstitution) {
        return userFocusInstitutionMapper.updateByUserIdInstitutionId(userFocusInstitution) == 1;

    }

    @Override
    public HashMap<String, Object> findUserFocusInstitutionContentByUserId(String user_id, Integer currentPage,
                                                                           Integer pageSize) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Integer totalCount = userFocusInstitutionMapper.getCountFocusInstitution(user_id);
        Integer totalPage = (int) Math.ceil(totalCount / pageSize + 0.4);
        if (currentPage > totalPage)
            currentPage = totalPage;
        map.put("user_id", user_id);
        map.put("start", (currentPage - 1) * pageSize);
        map.put("end", pageSize);
        List<UserFocusInstitutionInfo> list = userFocusInstitutionMapper.findUserFocusInstitutionContentByUserId(map);
        map.clear();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("list", list);
        return map;
    }
}
