package com.whatdo.keep.util;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class CustomFileupload {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomFileupload.class);
	
	
	
	
	public static String uploadQR(String rootPath,String uploadPath,String savedName ,String originalName, byte[] fileData) throws Exception {
		
		//겹쳐지지 않는 파일명을 위한 유니크한 값 생성
		UUID uid = UUID.randomUUID();
		
		//원본파일 이름과 UUID 결합
//		String savedName = uid.toString() + "_" + originalName;
		//파일을 저장할 폴더 생성(년 월 일 기준)
//		System.out.println("File.separator+rootPath + File.separator +uploadPath");
//		System.out.println(File.separator+rootPath + File.separator +uploadPath);
//		File dirPath = new File(File.separator+rootPath + File.separator +uploadPath);
		File dirPath = new File(rootPath + File.separator +uploadPath);
		
//		File dirPath = new File("fileupload"+ File.separator +uploadPath);
		
		
		
			if(!dirPath.exists()) {
				System.out.println("존재 안함.");
				dirPath.mkdirs();
			}//if		
		//저장할 파일준비
//		File target = new File(File.separator+rootPath + File.separator+uploadPath, savedName);
		File target = new File(rootPath+ File.separator +uploadPath, savedName);
		
		//파일을 저장
		FileCopyUtils.copy(fileData, target);
		
		return originalName;
	}
	
	
	

	public static String uploadFile(String rootPath,String uploadPath,String savedName ,String originalName, byte[] fileData) throws Exception {
		
		//겹쳐지지 않는 파일명을 위한 유니크한 값 생성
		UUID uid = UUID.randomUUID();
		
		//원본파일 이름과 UUID 결합
//		String savedName = uid.toString() + "_" + originalName;
		//파일을 저장할 폴더 생성(년 월 일 기준)
//		System.out.println("File.separator+rootPath + File.separator +uploadPath");
//		System.out.println(File.separator+rootPath + File.separator +uploadPath);
//		File dirPath = new File(File.separator+rootPath + File.separator +uploadPath);
		File dirPath = new File(rootPath + File.separator +uploadPath);
		
//		File dirPath = new File("fileupload"+ File.separator +uploadPath);
		
		
		
			if(!dirPath.exists()) {
				System.out.println("존재 안함.");
				dirPath.mkdirs();
			}//if		
		//저장할 파일준비
//		File target = new File(File.separator+rootPath + File.separator+uploadPath, savedName);
		File target = new File(rootPath+ File.separator +uploadPath, savedName);
		
		System.out.println("save file path : "+target.getCanonicalPath());
		
		//파일을 저장
		FileCopyUtils.copy(fileData, target);
		
		return originalName;
	}
	
	public static boolean deletedFile(String rootPath,String uploadPath,String savedName) throws Exception {
		
		//겹쳐지지 않는 파일명을 위한 유니크한 값 생성
		UUID uid = UUID.randomUUID();
		
		//원본파일 이름과 UUID 결합
//		String savedName = uid.toString() + "_" + originalName;
		//파일을 저장할 폴더 생성(년 월 일 기준)
//		System.out.println("File.separator+rootPath + File.separator +uploadPath" );
//		System.out.println(File.separator+rootPath + File.separator +uploadPath+File.separator +savedName);
//		File target = new File("fileupload"+ File.separator +uploadPath, savedName);
		File target = new File(rootPath + File.separator +uploadPath, savedName);
		
    	if( target.exists() ){
    		if(target.delete()){
    			System.out.println("파일삭제 성공");
    			return true;
    		}else{
    			System.out.println("파일삭제 실패");
    			return false;
    		}
    	}else{
    		return true;
    	}

	}
	
	
}