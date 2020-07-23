package com.dabai.serviceImpl.Institution;


import com.dabai.dto.SomeInfo.PageInfo;
import api.Institution.InstitutionService;
import com.dabai.dao.Institution.InstitutionMapper;
import com.dabai.dto.Institution.Institution;
import com.dabai.dto.Institution.InstitutionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author dabai:
 * <p>
 * 类说明  InstitutionService实现类
 */
@DubboService
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionMapper institutionMapper;

    @Override
    public Boolean addInstitution(Institution institution) {
        return institutionMapper.insertInstitution(institution) == 1;

    }

    @Override
    public Boolean updateInstitutionById(Institution institution) {
        return institutionMapper.updateInstitution(institution) == 1;

    }

    @Override
    public List<Institution> findInstitutionList(PageInfo pageInfo) {
        Integer totalCount = 4;
        Integer totalPage = (int) Math.ceil(totalCount / pageInfo.getPagesize() + 0.4);
        if (pageInfo.getCurrentpage() > totalPage) {
            pageInfo.setCurrentpage(totalPage);
        }
        return institutionMapper.findInstitutionList((pageInfo.getCurrentpage() - 1) * pageInfo.getPagesize(), pageInfo.getPagesize());

    }

    @Override
    public Integer getCount() {
        return institutionMapper.getCountInstitution();
    }

    @Override
    public List<InstitutionInfo> findInstitutionInfo(PageInfo pageInfo) {
        Integer currentpage = pageInfo.getCurrentpage();
        Integer pagesize = pageInfo.getPagesize();
        return institutionMapper.findInstitutionInfo((currentpage - 1) * pagesize, pagesize);
    }

    @Override
    public InstitutionInfo getInstitutionInfoById(Long insid) {

        return institutionMapper.getInstitutionInfoById(insid);
    }


}
