package com.whatdo.keep.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.ChartDataVO;
import com.whatdo.keep.vo.MemberVO;

@Repository
public class AddressCodeDAOImpl implements AddressCodeDAO{

	
	@Autowired
	private SqlSession sqlsession;
	private static final String NAMESPACE = "mapper.address.";

	@Override
	public List<AddressCodeVO> getCitys() {

		return sqlsession.selectList( NAMESPACE+"getCitys");
	}


	@Override
	public List<AddressCodeVO> getGus(Map<String, String> param) {
		return sqlsession.selectList( NAMESPACE+"getGus",param);
	}


	@Override
	public List<AddressCodeVO> getDongs(Map<String, String> param) {
		return sqlsession.selectList( NAMESPACE+"getDongs",param);
	}

	@Override
	public String cityVal(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"cityVal",param);
	}

	@Override
	public String gunVal(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"gunVal",param);
	}

	@Override
	public String dongVal(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"dongVal",param);
	}

	@Override
	public Integer getenterdangwonAll(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"getenterdangwonAll",param);
	}


	@Override
	public Integer getenterdangwon00(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"getenterdangwon00",param);
	}


	@Override
	public Integer getenterdangwon01(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"getenterdangwon01",param);
	}

	@Override
	public List<ChartDataVO> getenterchart01(Map<String, String> param) {
		return sqlsession.selectList( NAMESPACE+"getenterchart01",param);
	}
	
	@Override
	public List<ChartDataVO> getenterchart02(Map<String, String> param) {
		return sqlsession.selectList( NAMESPACE+"getenterchart02",param);
	}

	@Override
	public List<AddressCodeVO> getCitysChart(Map<String,String> param) {
		return sqlsession.selectList( NAMESPACE+"getCitysChart",param);
	}


	@Override
	public Integer gettotaldangwon(Map param) {
		return sqlsession.selectOne( NAMESPACE+"gettotaldangwon",param);
	}
	
	@Override
	public Integer getenterdangwon00d(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"getenterdangwon00d",param);
	}


	@Override
	public Integer getenterdangwon01d(Map<String, String> param) {
		return sqlsession.selectOne( NAMESPACE+"getenterdangwon01d",param);
	}


	@Override
	public List<AddressCodeVO> getdistrict_district() {
		return sqlsession.selectList( NAMESPACE+"getdistrict_district");
	}
	
	@Override
	public List<AddressCodeVO> getCitys_district() {
		return sqlsession.selectList( NAMESPACE+"getCitys_district");
	}


	@Override
	public List<AddressCodeVO> getGuns_district(Map<String, String> param) {
		return sqlsession.selectList( NAMESPACE+"getGuns_district",param);
	}


	@Override
	public List<MemberVO> getmember_fromdistrict(Map<String, Object> param) {
		return sqlsession.selectList( NAMESPACE+"getmember_fromdistrict",param);
	}


	@Override
	public Integer getmember_fromdistrict_count(Map<String, Object> param) {
		return sqlsession.selectOne(NAMESPACE+"getmember_fromdistrict_count",param);
	}


}
