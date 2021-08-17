package com.whatdo.keep.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.GroupVO;

@Repository
public interface GroupVORepository extends JpaRepository<GroupVO, Long>{

	GroupVO findBySeq(Long seq);
	GroupVO findByGroupKey(String groupKey);
	Page<GroupVO> findAll(Specification<GroupVO> condition,Pageable pageable);
	
	
}
