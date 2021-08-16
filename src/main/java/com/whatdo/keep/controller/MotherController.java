package com.whatdo.keep.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;

import com.whatdo.keep.repository.GroupVORepository;
import com.whatdo.keep.repository.MemberVORepository;


@Controller
public class MotherController {

	@Autowired
	public GroupVORepository groupVORepository;
	
	@Autowired
	public MemberVORepository memVoRepository;
	
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
		
		String orderDirectionTargetstr = orderDirectionTarget[0];
		int editPage = start /length; 
		Pageable p = PageRequest.of(editPage, length == 0 ? 10 : length);
		if(orderDirectionTargetstr.contentEquals("asc")) {
			p = PageRequest.of(editPage, length == 0 ? 10 : length,  Sort.Direction.ASC,targetColumnName);
		}else {
			p = PageRequest.of(editPage, length == 0 ? 10 : length, Direction.DESC,targetColumnName);
		}
		
		return p;
		
	}
}
