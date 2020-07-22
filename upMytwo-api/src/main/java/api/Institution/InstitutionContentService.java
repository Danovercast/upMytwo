package api.Institution;

import com.dabai.dto.Institution.InstitutionContent;

import java.util.List;

/**  
* @author dabai: 

* 类说明  InstitutionContentService接口
*/
public interface InstitutionContentService {
	public Boolean addInstitutionContent(InstitutionContent institutionContent);
	public Boolean updateInstitutionContentById(InstitutionContent institutionContent);
	public InstitutionContent getInstitutionContentByInstitutionId(Long institution_id);
	public List<InstitutionContent> findInstitutionContentListByInstitutionIds(Long[] institution_ids);

}
