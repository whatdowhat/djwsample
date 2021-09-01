package com.whatdo.keep.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.ChartDataVO;

@Service
public interface AddressCodeDAO {

	List<AddressCodeVO> getCitys();
	List<AddressCodeVO> getGus(Map<String,String> param);
	List<AddressCodeVO> getDongs(Map<String,String> param);
	
	
	String cityVal(Map<String,String> param);
	String gunVal(Map<String,String> param);
	String dongVal(Map<String,String> param);
	
	Integer getenterdangwonAll(Map<String,String> param);
	Integer getenterdangwon00(Map<String,String> param);
	Integer getenterdangwon01(Map<String,String> param);

	Integer getenterdangwon00d(Map<String,String> param);
	Integer getenterdangwon01d(Map<String,String> param);
	
	List<ChartDataVO> getenterchart01(Map<String,String> param);
	List<ChartDataVO> getenterchart02(Map<String,String> param);
	List<AddressCodeVO> getCitysChart(Map<String,String> param);
	Integer gettotaldangwon(Map param);
	
	/* 선거구별 */
	
	List<AddressCodeVO> getdistrict_district();
	List<AddressCodeVO> getCitys_district();
	List<AddressCodeVO> getGuns_district(Map<String, String> param);
	
	
}
