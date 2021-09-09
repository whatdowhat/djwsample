package com.whatdo.keep.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.whatdo.keep.config.em.AuthorityEm;
import com.whatdo.keep.config.handler.CustomAccessDeniedHandler;
import com.whatdo.keep.config.handler.CustomAuthenticationFailHandler;
import com.whatdo.keep.config.handler.CustomAuthenticationSuccessHandler;
import com.whatdo.keep.config.handler.CustomLogoutHandler;


@Configuration
@EnableWebSecurity
@PropertySource(value = {"classpath:config.properties", "classpath:web.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${config.happyAPI}")
	private String happyAPI;
	
	@Override
	public void configure(WebSecurity web) throws Exception {		
			

//		    web.httpFirewall(defaultHttpFirewall());
		    web.ignoring()
		    .antMatchers("/css/**", "/js/**", "/resources/**","/orderSystemfileupload/**","/img/**","/assets/**", "/lib/**","/favicon.ico");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8");
//		CustomFilter filter2 = new CustomFilter();
//		http.addFilterBefore(filter, CsrfFilter.class);
//		http.addFilterBefore(filter2, BasicAuthenticationFilter.class);

//		http.authorizeRequests().antMatchers("*").permitAll().and().csrf().disable().httpBasic();;
//		
				
		System.out.println("############# happyAPI:"+happyAPI);
//		Environment env = (Environment) ApplicationContextProvider.getApplicationContext()
//				.getBean("environment");
//		String happyAPI = env.getProperty("config.happyAPI");
		
//		http.headers().frameOptions().disable();
//		CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8");
//		CustomFilter filter2 = new CustomFilter();
//		http.addFilterBefore(filter, CsrfFilter.class);
//		http.addFilterBefore(filter2, BasicAuthenticationFilter.class);
		http.authorizeRequests()
		.antMatchers(
				
				"/",
				happyAPI,
				"/login.do",
				"https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb",
				"http://www.test.co.kr/checkplus_success.jsp",
				"http://www.test.co.kr/checkplus_fail.jsp",
				"/index.jsp"

//				"/favicon.ico",
//				"/restapi/post/order.do",
//				"/restapi/post/isComplete.do",
//				"/restapi/post/CancelOrder.do",
//				"/restapi/post/getOrderItems.do",
//				"/restapi/post/getOrderMaster.do",
//				"/restapi/post/getSpecificationImage.do",
//				"/comm/**",
//				"/index.jsp", "/login.do", "/registration.do", "/h2/**","/loginFirst.do","/loginPasswrod.do","/error/**","/test/**","downloadFile","/agreeAgrm1.do"
				
				)
		
		.permitAll()
		.antMatchers("/public/**").permitAll()
		
//		.antMatchers("/admin/**").hasAuthority(AuthorityEm.ADMIN.getName())
		.antMatchers("/admin/**").hasAnyAuthority(AuthorityEm.ADMIN.getName(),AuthorityEm.USER.getName(),"ROLE_전국","ROLE_시군구","ROLE_읍면동")
//		.antMatchers("/admin/**").hasAnyRole(AuthorityEm.ADMIN.getName(),AuthorityEm.USER.getName(),"ROLE_전국","ROLE_시군구","ROLE_읍면동")
		
		.antMatchers("/user/**").hasAuthority(AuthorityEm.USER.getName())
		.antMatchers("/guest/**").hasAuthority("USER2")
		
		.anyRequest().authenticated()
		.and()
	.formLogin()
		.loginPage("/login.do")
		.successHandler(customAuthenticationSuccessHandler())
		.failureHandler(customAuthenticationFailHandler())
		.usernameParameter("id")
		.passwordParameter("password")
		.and()
	.logout()
		.logoutSuccessHandler(new CustomLogoutHandler())
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
		.logoutSuccessUrl("/login.do")
		.and()
	.exceptionHandling()
		.accessDeniedHandler(customAccessDeniedHandler())
		.authenticationEntryPoint(authenticationEntryPoint())
		.and()
		
    .csrf().disable().httpBasic();
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		
				

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new CustomAuthProvider();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}	

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint("/");
	}
	
	
	@Bean
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

	@Bean
	public CustomAuthenticationFailHandler customAuthenticationFailHandler() {
		return new CustomAuthenticationFailHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	}

}
