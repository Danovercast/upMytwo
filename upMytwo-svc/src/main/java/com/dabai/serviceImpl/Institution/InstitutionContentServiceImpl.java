package com.dabai.serviceImpl.Institution;


import api.Institution.InstitutionContentService;
import com.dabai.dao.Institution.InstitutionContentMapper;
import com.dabai.dto.Institution.InstitutionContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**  
* @author dabai: 

* 类说明  InstitutionContentService实现类
*/
@DubboService
public class InstitutionContentServiceImpl implements InstitutionContentService {

	@Autowired
	private InstitutionContentMapper institutionContentMapper;

	@Override
	public Boolean addInstitutionContent(InstitutionContent institutionContent) {
		return institutionContentMapper.insertInstitutionContent(institutionContent)==1;
		
	}

	@Override
	public Boolean updateInstitutionContentById(InstitutionContent institutionContent) {
		return institutionContentMapper.updateInstitutionContent(institutionContent)==1;
		
	}

	@Override
	public InstitutionContent getInstitutionContentByInstitutionId(Long institution_id) {
		InstitutionContent institutionContent = institutionContentMapper.findInstitutionContentByInstitutionId(institution_id);
		return institutionContent;
		
	}

	@Override
	public List<InstitutionContent> findInstitutionContentListByInstitutionIds(Long[] institution_ids) {
		
		return institutionContentMapper.findInstitutionContentListByInstitutionIds(institution_ids);
	}
 
 

	 

}
