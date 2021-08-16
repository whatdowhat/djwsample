package com.whatdo.keep.config;

import java.util.List;
import java.util.Properties;

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
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.whatdo.keep.util.CustomExcel;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@ComponentScan(basePackages = "com.whatdo.keep.*") //서버기동용
@ComponentScan(basePackages = "com.whatdo.keep.config.* com.whatdo.keep.repository.* com.whatdo.keep.vo.*") //junit test용
@EnableJpaRepositories(basePackages = "com.whatdo.keep.*")
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
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
	
	
	
	@Autowired
	Environment env;
	
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@Autowired
	HttpSession httpSession;
	
	
	
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
		//props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.dialect", dialect);
		
		
		props.setProperty("hibernate.SQL", "debug");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		props.setProperty("hibernate.use_sql_comments", "true");
		props.setProperty("logging.level.org.hibernate.type.descriptor.sql", "trace");
		
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
	
	
//	@Bean
//	public CommonsMultipartResolver commonsMultipartResolver(){
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setDefaultEncoding("utf-8");
//		commonsMultipartResolver.setMaxInMemorySize(fileuploadMax);
//		File dirPath = new File(fileuploadUrl);
//		
//		if(!dirPath.exists()) {
//			dirPath.mkdir();
//		}//if
//		
//		return commonsMultipartResolver;
//		
//	}
	
	
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
