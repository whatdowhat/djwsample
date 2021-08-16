package com.whatdo.keep.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;

@Repository
public interface MemberVORepository extends JpaRepository<MemberVO, Long>{

	GroupVO findBySeq(Long seq);
	Page<MemberVO> findAll(Specification<MemberVO> condition,Pageable pageable);
	List<MemberVO> findAll(Specification<MemberVO> condition);
	
}
