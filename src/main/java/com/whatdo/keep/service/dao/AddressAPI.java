package com.whatdo.keep.service.dao;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.whatdo.keep.vo.AddressAPIVO;

public interface AddressAPI {

	List<AddressAPIVO> getList(MultiValueMap<String, String> map);
}
