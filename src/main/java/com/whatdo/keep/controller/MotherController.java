package com.whatdo.keep.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.whatdo.keep.repository.GroupVORepository;
import com.whatdo.keep.repository.InnerMessageVORepository;
import com.whatdo.keep.repository.InnerNoticeVORepository;
import com.whatdo.keep.repository.MemberVORepository;


@Controller
public class MotherController {

	@Autowired
	public GroupVORepository groupVORepository;
	
	@Autowired
	public MemberVORepository memVoRepository;
	
	@Autowired
	public InnerMessageVORepository innerMessageVORepository;
	
	@Autowired
	public InnerNoticeVORepository innerNoticeVORepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(MotherController.class);
	
	
	public Pageable getPageable(HttpServletRequest req, int start , int length) {
		
		Map<String, String[]> m = req.getParameterMap();
		
		String[] columSize = m.get("columnsize");
		int columSizeInt = Integer.valueOf(columSize[0]);
		
		String colmunNames[] = new String[columSizeInt];
		for(int i=0; i<columSizeInt;i++) {
			String[]  tem = m.get("columns["+i+"][data]");
			colmunNames[i] = tem[0]; //컬럼 이름  및 순서 가져옴
		}

		String[]  orderTarget = m.get("order[0][column]");
		String[]  orderDirectionTarget = m.get("order[0][dir]");
		int orderTargetIndex= Integer.valueOf(orderTarget[0]);
		String targetColumnName = colmunNames[orderTargetIndex];
		
		System.out.println("orderTargetIndex  " +orderTargetIndex);
		System.out.println("targetColumnName  " +targetColumnName);
		String orderDirectionTargetstr = orderDirectionTarget[0];
		int editPage = start /length; 
		Pageable p = PageRequest.of(editPage, length == 0 ? 10 : length);
		if(orderDirectionTargetstr.contentEquals("asc")) {
			p = PageRequest.of(editPage, length == 0 ? 10 : length,  Sort.Direction.ASC,targetColumnName);
		}else {
			p = PageRequest.of(editPage, length == 0 ? 10 : length, Sort.Direction.DESC,targetColumnName);
		}
		
		return p;
		
	}
	
	public Map<String,String> getAuthentics() {
		 //시큐리티 컨텍스트 객체를 얻습니다.
		Map<String, String> result = new HashMap<String, String>();
		SecurityContext context =SecurityContextHolder.getContext(); 
		// 인증 객체를 얻습니다.
		 Authentication authentication = context.getAuthentication(); // 로그인한 사용자정보를 가진 객체를 얻습니다.
		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); 
		 Iterator<? extends GrantedAuthority> iter = authorities.iterator(); 
		 while (iter.hasNext()) { 
			 GrantedAuthority auth =  iter.next(); 
//			 System.out.println(auth.getAuthority()); 
			 result.put(auth.getAuthority(), auth.getAuthority());
		 }
		return result;
	}
}
