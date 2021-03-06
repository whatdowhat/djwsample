package com.whatdo.keep.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.whatdo.keep.config.em.AuthorityEm;
import com.whatdo.keep.repository.MemberVORepository;
import com.whatdo.keep.vo.MemberVO;

@Component
public class CustomAuthProvider implements AuthenticationProvider, InitializingBean {

	static final Logger log = LoggerFactory.getLogger(CustomAuthProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		return new UsernamePasswordAuthenticationToken("test", "test",grantedAuthorityList);
		
		
		
//		System.out.println("AUTHPROVIDER:"+ApplicationContextProvider.getApplicationContext().getApplicationName());
		
		Environment env = (Environment) ApplicationContextProvider.getApplicationContext()
				.getBean("environment");
		MemberVORepository memberVORepository = (MemberVORepository) ApplicationContextProvider.getApplicationContext()
				.getBean("memberVORepository");
		String administrator = env.getProperty("web.administrator");
		String administratorPassword = env.getProperty("web.administratorPassword");
		
		String username = authentication.getName(); 
		String password = (String)authentication.getCredentials();	

//		System.out.println("AUTHPROVIDER:"+administrator);
//		System.out.println("AUTHPROVIDER:"+administratorPassword);
//		System.out.println("AUTHPROVIDER:"+username);
//		System.out.println("AUTHPROVIDER:"+password);
		if(administrator.equals(username)){
			if(administratorPassword.equals(password)){
				
				log.debug("administrator login success");
				List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
				grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.ADMIN.getName()));
				return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
			}else{//???????????? ??????.
				throw new BadCredentialsException("administrator password fail");
			}
		}else{
			System.out.println("username:::"+username);
			
			try {
				password = CryptoOnewayPasswrod.encryptPassword(password,username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			MemberVO user =  memberVORepository.findByPhone(username);
			if(user == null ) {
//			if(user.getPhonePassword()==null || user.getAdminAuth() == null ) {
//				throw new BadCredentialsException("user is not exist");
				
				throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.badCredentials");
			}
			if(user.getPhonePassword()==null || user.getAdminAuth() == null ) {
				throw new BadCredentialsException("user is not exist");
			}
			
			if(user.getPhonePassword().equals(password)){
				if(user.getAdminAuth().equals("02")) { //??????
					System.out.println("?????? ?????? :"+username);
					if(user.getLevel().equals("??????")) {
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.ADMIN.getName()));
						
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);	
					}else if(user.getLevel().equals("??????")){
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.USER.getName()));
						grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_??????"));
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
					}else if(user.getLevel().equals("?????????")){
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.USER.getName()));
						grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_?????????"));
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
					}else if(user.getLevel().equals("?????????")){
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.USER.getName()));
						grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_?????????"));
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
					}else {
						throw new BadCredentialsException("user is not exist");
					}
				}else {
					System.out.println("?????? ????????? :"+username);
					if(user.getLevel().equals("??????")) {
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.ADMIN.getName()));
						System.out.println("auth size :" +grantedAuthorityList.size());
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);	
					}else if(user.getLevel().equals("??????")){
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.USER.getName()));
						System.out.println("auth size :" +grantedAuthorityList.size());
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
					}else if(user.getLevel().equals("?????????")){
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.USER.getName()));
						System.out.println("auth size :" +grantedAuthorityList.size());
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
					}else if(user.getLevel().equals("?????????")){
						List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
						grantedAuthorityList.add(new SimpleGrantedAuthority(AuthorityEm.USER.getName()));
						
						System.out.println("auth size :" +AuthorityEm.USER.getName());
						System.out.println("auth size :" +grantedAuthorityList.size());
						return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
					}else {
						throw new BadCredentialsException("user is not exist");
					}
				}
				
				
			}else {
				throw new BadCredentialsException("user is not exist");
			}
		}
		
		
		
		
//TEST CODE
//		log.debug("enter =================>{}","CustomAuthProvider");
//		log.debug("enter {}","CustomAuthProvider");
//		
//		MemberInfoVO m =  memberVORepository.findBySeq(5);
//		
//		m.getMyAuthorities().forEach(System.out::println);
//		
//		System.out.println("######## userseq from authority ");
//		System.out.println(cmAuthorityRepository.findBySeq(10).getMemberInfoVO().getSeq());
//		
//		
//		AuthorityVO authorityVO = new AuthorityVO();
//		authorityVO.setAuthority(AuthorityEm.USER);
//		authorityVO.setUserSeq(5);
//		authorityVO.setRegDt(new Date());
//		
//		authorityVO = cmAuthorityRepository.save(authorityVO);
//		System.out.println(authorityVO);
//		
//		authorityVO = new AuthorityVO();
//		authorityVO.setAuthority(AuthorityEm.ADMIN);
//		authorityVO.setUserSeq(5);
//		authorityVO.setRegDt(new Date());
//		
//		authorityVO = cmAuthorityRepository.save(authorityVO);
//		  String username = authentication.getName(); 
//		  String password = (String)authentication.getCredentials(); 
//		
//		  memberVORepository.delete(m);
		  
//		  List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
//		  grantedAuthorityList.add(new SimpleGrantedAuthority("USER"));
//		  return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorityList);
		
			
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("####afterPropertiesSet##########");

	}

}
