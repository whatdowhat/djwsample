package com.whatdo.keep.service.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.whatdo.keep.service.dao.TimeDAO;
import com.whatdo.keep.vo.Customer;

@Repository
public class TimeDAOImpl implements TimeDAO{

	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NAMESPACE = "mapper.sample.";
	
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return sqlsession.selectOne( NAMESPACE+"getTime");
	}

	@Override
	public Customer getCustomer2(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne( NAMESPACE+"getCustomer2",param);
	}

}
