package com.whatdo.keep.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.SystemCommonVO;

@Repository
public interface SystemCommonVORepository extends JpaRepository<SystemCommonVO, BigDecimal>{

	SystemCommonVO findBySeq(BigDecimal seq);
	List<SystemCommonVO> findAll();
	
}
