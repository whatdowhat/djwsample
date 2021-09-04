package com.whatdo.keep.config.handler;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import com.whatdo.keep.config.ApplicationContextProvider;
import com.whatdo.keep.config.em.AuthorityEm;
import com.whatdo.keep.repository.InnerNoticeVORepository;
import com.whatdo.keep.repository.MemberVORepository;
import com.whatdo.keep.vo.InnerNotice;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	private final String DEFAULT_FAILURE_URL = "/login.do?error=true";
	private final String DEFAULT_LOGIN_FIRST= "/loginFirst.do";
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
//	private String DEFAULT_LOGIN_SUCCESS_URL = "/user/home.do";
	
	////////////////////////////////////////////////////////////////////////////////
	//< public functions (override)

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		//< clear authentication error
		clearAuthenticationAttributes(request);
		//< redirect page
		redirectStrategy(request, response, authentication);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//< private functions
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
	
	private void redirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(roles.contains(AuthorityEm.ADMIN.getName())) {
			redirectStratgy.sendRedirect(request, response, "/admin/loginAfter.do");
		}else if(roles.contains(AuthorityEm.USER.getName())) {
			
			MemberVORepository memberVORepository = (MemberVORepository) ApplicationContextProvider.getApplicationContext()
					.getBean("memberVORepository");
			request.getSession().setAttribute("member",memberVORepository.findByPhone(authentication.getName()));
			
			InnerNoticeVORepository innerNoticeVORepository = (InnerNoticeVORepository) ApplicationContextProvider.getApplicationContext()
					.getBean("innerNoticeVORepository");
			List<InnerNotice> notices =innerNoticeVORepository.findAll();
			request.getSession().setAttribute("notices",notices);
			
			
			redirectStratgy.sendRedirect(request, response, "/admin/loginAfter.do");
//			redirectStratgy.sendRedirect(request, response, "/user/loginAfter.do");
		}
	}

}
