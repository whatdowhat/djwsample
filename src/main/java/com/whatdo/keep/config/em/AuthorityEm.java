package com.whatdo.keep.config.em;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum AuthorityEm {

	ADMIN("ROLE_ADMIN",Arrays.asList("ROLE_ADMIN","ROLE_ADMIN2")),
	USER("ROLE_USER",Arrays.asList("ROLE_USER","ROLE_ROLE_USER2")),
	GUEST("GUEST",Arrays.asList("","GUEST"));
	
	private String name;
	private List<String> list;
	AuthorityEm(String name,List<String> list) {
		this.name = name;
		this.list = list;
	}
	public static AuthorityEm findByAuthoridCode(String code){
		
		return Arrays.stream(AuthorityEm.values())
				.filter(group -> group.hasAuthorityCode(code))
				.findAny().orElse(GUEST);
		
	}

	public boolean hasAuthorityCode(String code){
	    return list.stream()
	            .anyMatch(authority -> authority.equals(code));
	}
	
}
