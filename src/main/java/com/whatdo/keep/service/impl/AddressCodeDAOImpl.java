package com.whatdo.keep.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;

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


}
