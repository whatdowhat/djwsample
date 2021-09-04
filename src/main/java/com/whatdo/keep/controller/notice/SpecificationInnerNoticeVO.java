package com.whatdo.keep.controller.notice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.whatdo.keep.vo.InnerNotice;
import com.whatdo.keep.vo.MemberVO;


@Component
public class SpecificationInnerNoticeVO {

    public static Specification<InnerNotice> withCondition(Map<String, Object> condition) {

         return (Specification<InnerNotice>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(condition, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
  }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> conditino, Root<InnerNotice> root,
			CriteriaBuilder builder) {
		 List<Predicate> predicate = new ArrayList<>();
		 
	        for (String key : conditino.keySet()) {
		            if("seq".equals(key)){ 
		            	if(conditino.get(key).equals("")){
		            	}else{
		            		predicate.add(builder.equal(root.get(key), conditino.get(key)));	
		            	}
		            	
		            	
		            }else if("frommemberName".equals(key)){  
		            	if(conditino.get(key).equals("")){
	//	            		predicate.ad
		            	}else{
		            		predicate.add(builder.equal(root.get(key), conditino.get(key)));	
		            	}
				               
	
		            }else if("messageTitle".equals(key)){  
		            	if(conditino.get(key).equals("")){
	//	            		predicate.ad
		            	}else{
		            		predicate.add(builder.equal(root.get(key), conditino.get(key)));	
		            	}
				               
	
		            }else if("messageTxt".equals(key)){
		            	if(conditino.get(key).equals("")){
		            		
		            	}else{
		            		predicate.add(builder.equal(root.get(key), conditino.get(key)));
		            	}
		                
			        }else if("readDt".equals(key)){ // 'partner' 조건은 partner객체 안에 있는 keword데이터를 2차 가공하여 검색
		            	
			            	if(conditino.get(key).equals("")){
			            	}else{
			            		predicate.add(builder.between(root.get("readDt"),(Date)conditino.get("startDate"), (Date)conditino.get("endDate")));
			            	}
			                
		            }else if("regDt".equals(key)){ // 'partner' 조건은 partner객체 안에 있는 keword데이터를 2차 가공하여 검색
		            	
		            	if(conditino.get(key).equals("")){
		            	}else{
		            		predicate.add(builder.between(root.get("regDt"),(Date)conditino.get("startDate"), (Date)conditino.get("endDate")));
		            	}
		                
			        }else{ // 'name', 'partner' 이외의 모든 조건 파라미터에 대해 equal 검색
	            }
	        }
	        return predicate;
	}
	
}
