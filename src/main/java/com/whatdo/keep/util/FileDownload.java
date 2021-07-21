package com.whatdo.keep.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownload extends AbstractView {
	public void Download() {

		setContentType("application/download; utf-8");

	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		File file = (File) model.get("downloadFile");
		String fileN = (String) model.get("fileName");

		response.setContentType(getContentType());
		response.setContentLength((int) file.length());

		// String userAgent = request.getHeader("User-Agent");

		// boolean ie = userAgent.indexOf("MSIE") > -1;

		String fileName = null;
		String header = request.getHeader("User-Agent");
		fileN = getFileNm(header,fileN);

		response.setHeader("Content-Disposition", "attachment; filename=" + fileN);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", "application/octet-stream");

		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		
		response.getHeaderNames().stream().forEach(System.out::println);
		
		// 파일 카피 후 마무리
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		} // try end;
		out.flush();
		out.close();

	}

	public String getFileNm(String browser, String fileNm) {
		String reFileNm = null;
		try {
			if (browser.equals("MSIE") || browser.equals("Trident") || browser.equals("Edge")) {
				
				reFileNm = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
			} else {
				if (browser.equals("Chrome")) {
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < fileNm.length(); i++) {
						char c = fileNm.charAt(i);
						if (c > '~') {
							sb.append(URLEncoder.encode(Character.toString(c), "UTF-8"));
						} else {
							sb.append(c);
						}
					}
					reFileNm = sb.toString();
				} else {
					reFileNm = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
				}
				if (browser.equals("Safari") || browser.equals("Firefox")){
					reFileNm = URLDecoder.decode(reFileNm);
				}else{
				}
					
			}
		} catch (Exception e) {
		}
		return reFileNm;
	}

}
