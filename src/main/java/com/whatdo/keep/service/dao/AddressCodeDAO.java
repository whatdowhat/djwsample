package com.whatdo.keep.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.whatdo.keep.vo.AddressCodeVO;

@Service
public interface AddressCodeDAO {

	List<AddressCodeVO> getCitys();
	List<AddressCodeVO> getGus(Map<String,String> param);
	List<AddressCodeVO> getDongs(Map<String,String> param);
	
	
	String cityVal(Map<String,String> param);
	String gunVal(Map<String,String> param);
	String dongVal(Map<String,String> param);
	

	
}
