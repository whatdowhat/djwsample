package com.whatdo.keep.controller.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.whatdo.keep.vo.MemberVO;


@Component
public class SpecificationMmemberVO {

    public static Specification<MemberVO> withCondition(Map<String, Object> condition) {

         return (Specification<MemberVO>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(condition, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
  }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> conditino, Root<MemberVO> root,
			CriteriaBuilder builder) {
		 List<Predicate> predicate = new ArrayList<>();
	        for (String key : conditino.keySet()) {
	            if("cityCode".equals(key)){ 
	            	if(conditino.get(key).equals("")){
	            	}else{
	            		predicate.add(builder.equal(root.get(key), conditino.get(key)));	
	            	}
	            	
	            	
	            }else if("gunCode".equals(key)){  
	            	if(conditino.get(key).equals("")){
//	            		predicate.ad
	            	}else{
	            		predicate.add(builder.equal(root.get(key), conditino.get(key)));	
	            	}
			               

	            }else if("dongCode".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.equal(root.get(key), conditino.get(key)));
	            	}
	                
	            }else if("church".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("churchRank".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("groupJikham".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("recommandName".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("cityN".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("gunN".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("dongN".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("name".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("level".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("phone".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("groupName".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
	            	}
	                
		        }else if("groupKey".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		System.out.println("spect:: 공백 ");
	            	}else{
	            		System.out.println("spect::  "+conditino.get(key));
	            		predicate.add(builder.equal(root.get(key), conditino.get(key)));
	            	}
	                
		        }else if("dangwon".equals(key)){
	            	if(conditino.get(key).equals("")){
	            		
	            	}else{
	            		predicate.add(builder.like(root.get(key), "%"+conditino.get(key)+"%"));
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
