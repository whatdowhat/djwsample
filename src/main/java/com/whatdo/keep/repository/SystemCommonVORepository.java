package com.whatdo.keep.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.SystemCommonVO;

@Repository
public interface SystemCommonVORepository extends JpaRepository<SystemCommonVO, BigDecimal>{

	SystemCommonVO findBySeq(BigDecimal seq);
	List<SystemCommonVO> findAll();
	
//	@Query("select u from SystemCommonVO u where 1=1")
//	List<Map<String,Object>> findAllNativeQueryTest1();
	
	@Query("select "
			+ " u.seq as seq"
			+ " ,u.intParam01 as intParm01"
			+ " ,u.strParam01 as strParm01"
			+ " from SystemCommonVO u where 1=1")
	List<Map<String,Object>> customQuery();
	
	@Query("select "
			+ " u.seq as seq"
			+ " ,u.intParam01 as intParm01"
			+ " ,u.strParam01 as strParm01"
			+ " from SystemCommonVO u where 1=1")
	Page<Map<String,Object>> customQuery2(Pageable pageable);
	
	
}
