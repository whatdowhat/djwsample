package com.whatdo.keep.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.InnerMessage;
import com.whatdo.keep.vo.InnerNotice;
import com.whatdo.keep.vo.MemberVO;

@Repository
public interface InnerNoticeVORepository extends JpaRepository<InnerNotice, Long>{

	
	Page<InnerNotice> findAll(Specification<InnerNotice> condition,Pageable pageable);
	List<InnerNotice> findAll(Specification<InnerNotice> condition);
	
}
