package com.whatdo.keep.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.whatdo.keep.util.CustomExcel;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@ComponentScan(basePackages = "com.whatdo.keep.*") //서버기동용
//@PropertySource(value = {"classpath:config.properties", "classpath:web.properties"})
@ComponentScan(basePackages = "com.whatdo.keep.config.* com.whatdo.keep.repository.* com.whatdo.keep.vo.*") //junit test용
@EnableJpaRepositories(basePackages = "com.whatdo.keep.*")
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ImportResource({"/WEB-INF/config/dispatcher-servlet.xml", "WEB-INF/config/spring/context-*.xml"})
@PropertySource(value = {"classpath:config.properties", "classpath:web.properties"})
public class ApplicationContextConfig implements WebMvcConfigurer {

	
	@Value("${config.path.imagefile}")
	private String pathImagefile;

	@Value("${config.mode}")
	private String configMode;
	
	
	@Value("${config.jdbcUrl}")
	private String jdbcUrl;
	
	@Value("${config.jdbcUser}")
	private String jdbcUser;
	
	@Value("${config.jdbcPassword}")
	private String jdbcPassword;
	
	@Value("${config.driverClassName}")
	private String driverClassName;
	
	@Value("${config.hibernate.dialect}")
	private String dialect;
	
	@Value("${config.fileuploadMax}")
	private Integer fileuploadMax;
	
	@Value("${config.fileuploadUrl}")
	private String fileuploadUrl;
	
	@Value("${config.sampleFilePath}")
	private String sampleFilePath;

	@Value("${config.happyAPI}")
	private String happyAPI;
	
	
	@Autowired
	Environment environment;
	
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@Autowired
	HttpSession httpSession;
	
	
	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		 return new ApplicationContextProvider();
	}
	
	
//	@Bean
//	public ViewResolver resolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/view", ".jsp");
//		return resolver;
//	}
	
	@Bean
	public CustomExcel CustomExcel(){
		return new CustomExcel();
	}
	
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    
	// HikariCP config

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
	
		  hikariConfig.setDriverClassName(driverClassName);
		  hikariConfig .setJdbcUrl(jdbcUrl); 
		  hikariConfig.setUsername(jdbcUser);
		  hikariConfig.setPassword(jdbcPassword);
		  
		  
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		

		return dataSource;
	}


	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public FactoryBean<EntityManagerFactory> entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		containerEntityManagerFactoryBean.setDataSource(dataSource());
		JpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
		containerEntityManagerFactoryBean.setJpaVendorAdapter(adaptor);
//		containerEntityManagerFactoryBean.setPackagesToScan("com.whatdo.keep.config.* com.whatdo.keep.repository.* com.whatdo.keep.vo.*");
		containerEntityManagerFactoryBean.setPackagesToScan("com.whatdo.keep.*");
		Properties props = new Properties();
//		System.out.println("jpa dialect :: "+dialect);
		
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		props.setProperty("hibernate.dialect", dialect);
		
		
//		props.setProperty("hibernate.SQL", "debug");
		props.setProperty("hibernate.SQL", "info");
//		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
//		props.setProperty("hibernate.use_sql_comments", "true");
		props.setProperty("hibernate.use_sql_comments", "false");
//		props.setProperty("logging.level.org.hibernate.type.descriptor.sql", "trace");
		//props.setProperty("tracespring.jpa.show-sql", "true");
		props.setProperty("tracespring.jpa.show-sql", "false");
		props.setProperty("spring.jpa.properties.hibernate.format_sql", "true");
		props.setProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", "true");
				
		containerEntityManagerFactoryBean.setJpaProperties(props);
		return containerEntityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
	
	// xxx.properties 
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}
	
	@Bean(name="httpServletRequest")
	public HttpServletRequest httpServletRequest() {

		return httpServletRequest;
	}

	@Bean(name="httpServletResponse")
	public HttpServletResponse httpServletResponse() {

		return httpServletResponse;
	}
	
	
	@Bean(name="httpSession")
	public HttpSession HttpSession() {

		return httpSession;
	}
	
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {

		BeanNameViewResolver excelView = new BeanNameViewResolver();
		excelView.setOrder(0);
		 
		 return excelView;
	}
	
	
	public static String uploadFile(String rootPath,String uploadPath,String savedName ,String originalName, byte[] fileData) throws Exception {
		
		//겹쳐지지 않는 파일명을 위한 유니크한 값 생성
		UUID uid = UUID.randomUUID();
		File dirPath = new File(rootPath + File.separator +uploadPath);
		
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
	
	
//	@Bean
//	public CommonsMultipartResolver commonsMultipartResolver(){
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setDefaultEncoding("utf-8");
//		commonsMultipartResolver.setMaxInMemorySize(fileuploadMax);
//		System.out.println("commonsMultipartResolver!!! created!");
////		File dirPath = new File(fileuploadUrl);
////		
////		if(!dirPath.exists()) {
////			dirPath.mkdir();
////		}//if
////		
//		
//		StandardServletMultipartResolver d;
//		return commonsMultipartResolver;
//		
//	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException{
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		commonsMultipartResolver.setMaxInMemorySize(fileuploadMax);
		System.out.println("commonsMultipartResolver!!! created!");
		File dirPath = new File(sampleFilePath);
		
//		
		if(!dirPath.exists()) {
			System.out.println(dirPath.getCanonicalPath());
			dirPath.mkdir();
		}else {
			System.out.println("alread !!"+dirPath.getCanonicalPath());
		}
//		
		
		StandardServletMultipartResolver d;
		return commonsMultipartResolver;
	}
	
	
//	@Bean(name="httpServletRequest")
//	public HttpServletRequest httpServletRequest() {
//
//		return httpServletRequest;
//	}
//
//	@Bean(name="httpServletResponse")
//	public HttpServletResponse httpServletResponse() {
//
//		return httpServletResponse;
//	}
//	
//	
//	@Bean(name="httpSession")
//	public HttpSession HttpSession() {
//
//		return httpSession;
//	}
//	
//	
//	
	
	
}
