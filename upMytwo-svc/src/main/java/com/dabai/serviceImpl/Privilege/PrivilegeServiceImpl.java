package com.dabai.serviceImpl.Privilege;

import api.Privilege.PrivilegeService;
import com.dabai.dao.Privilege.PrivilegeMapper;
import com.dabai.dto.Privilege.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  PrivilegeService实现类
 */
@DubboService
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public Boolean insertPrivilege(Privilege privilege) {
        return privilegeMapper.insertPrivilege(privilege) == 1;

    }

    @Override
    public Boolean updatePrivilege(Privilege privilege) {
        return privilegeMapper.updatePrivilege(privilege) == 1;

    }

    @Override
    public List<Privilege> findAllPrivilege() {
        return privilegeMapper.findAllPrivilege();
    }

}
