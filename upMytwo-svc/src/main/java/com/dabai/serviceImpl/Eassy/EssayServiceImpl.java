package com.dabai.serviceImpl.Eassy;

import api.Eassy.EssayService;
import com.dabai.dao.Eassy.EssayMapper;
import com.dabai.dto.Eassy.Essay;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  EssayService
 */
@DubboService
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayMapper essayMapper;

    @Override
    public boolean addEssay(Essay essay) {
        return essayMapper.addEssay(essay) == 1;
    }

    @Override
    public Essay findById(Long id) {
        return essayMapper.findById(id);
    }

    @Override
    public List<Essay> findSomeEssay() {
        List<Essay> list = essayMapper.findSomeEssay();
        return list;
    }

}
