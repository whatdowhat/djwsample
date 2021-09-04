package com.whatdo.keep.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.InnerMessage;
import com.whatdo.keep.vo.MemberVO;

@Repository
public interface InnerMessageVORepository extends JpaRepository<InnerMessage, Long>{

	
	Page<MemberVO> findAll(Specification<InnerMessage> condition,Pageable pageable);
	List<MemberVO> findAll(Specification<MemberVO> condition);
	
}
