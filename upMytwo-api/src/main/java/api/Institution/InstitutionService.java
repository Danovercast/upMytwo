package api.Institution;



import com.dabai.dto.SomeInfo.PageInfo;
import com.dabai.dto.Institution.Institution;
import com.dabai.dto.Institution.InstitutionInfo;

import java.util.List;

/**
 * @author dabai:
 * 
 *         类说明 institutionService 接口
 */
public interface InstitutionService {
	 Boolean addInstitution(Institution institution);

	 Boolean updateInstitutionById(Institution institution);

	 List<Institution> findInstitutionList(PageInfo pageInfo);

	 Integer getCount();

	 List<InstitutionInfo> findInstitutionInfo(PageInfo pageInfo);

	 InstitutionInfo getInstitutionInfoById(Long insid);

}
