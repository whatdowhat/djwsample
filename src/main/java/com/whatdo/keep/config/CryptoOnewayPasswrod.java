package com.whatdo.keep.config;

import java.security.MessageDigest;

public class CryptoOnewayPasswrod {

public static String encryptPassword(String password,String id) throws Exception{
		
		if(password == null) return "";
		if(id == null) return "";
		
		byte[] hashValue = null;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(id.getBytes());
		
		hashValue = md.digest(password.getBytes());
		
		return new String(org.apache.commons.codec.binary.Base64.encodeBase64(hashValue));
		
		
	}
	
}
