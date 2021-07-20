package com.whatdo.keep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.TestVO;

@Repository
public interface TestVORepository extends JpaRepository<TestVO, Long>{

	TestVO findBySeq(Integer seq);
	
}
