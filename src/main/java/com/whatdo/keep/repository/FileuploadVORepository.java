package com.whatdo.keep.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.vo.FileuploadVO;


@Repository
public interface FileuploadVORepository extends JpaRepository<FileuploadVO, Long>{

	FileuploadVO findBySeq(int seq);
	
	Page<FileuploadVO> findByMemberSeq(Integer memberSeq,Pageable pageable);
	Page<FileuploadVO> findAll(Pageable pageable);
	FileuploadVO findOne(Specification<FileuploadVO> condition);
	
	
}
