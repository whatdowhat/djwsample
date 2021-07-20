package com.whatdo.keep.service.dao;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.whatdo.keep.vo.Customer;

@Service
public interface TimeDAO {

	public String getTime();
	public Customer getCustomer2(Map<String,Object> param);
	
}
