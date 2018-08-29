package rzd.pktbcki.configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * User: VNikishin
 * Date: 07.06.18
 * Time: 18:40
 */

//@Configuration indicates that this class contains one or more bean methods annotated with @Bean
@Configuration

//@EnableWebMvc is equivalent to mvc:annotation-driven
@EnableWebMvc
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:validation.properties")
        //@PropertySource("classpath:mybatis.properties")//for apache shiro realm
//        @PropertySource(value = "file:${file.config.emm.properties}")
//        @PropertySource(value = "file:${file.config.emm.properties}", ignoreResourceNotFound=true)
})
//@PropertySource({ "classpath:application.properties" })

//@ComponentScan is equivalent to context:component-scan base-package="..."
@ComponentScan(basePackages = "rzd.pktbcki")

//enable the configuration of transactional behavior based on annotations
@EnableTransactionManagement

//Scanning for mapper @MapperScan rather than the <mybatis:scan/>
@MapperScan("rzd.pktbcki.mapper")

@Import({ShiroBeanConfiguration.class,
        ShiroAnnotationProcessorConfiguration.class,
        ShiroWebConfiguration.class,
        ShiroWebFilterConfiguration.class}) //shiro 1.4.0
public abstract class MyConfiguration extends WebMvcConfigurerAdapter {


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;
/*
     используем статический logger, чтобы избежать вопросов сериализации
     static to optimize serialization
     protect- available to subclasses
        
*/
/*
for slf4j
private static final Logger logger = LoggerFactory.getLogger( MyConfiguration.class );
*/
    
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    @Autowired
    private Environment env;
    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    @Autowired
    private org.springframework.context.ApplicationContext applicationContext;


    /*
 * STEP 1 - Create SpringResourceTemplateResolver
 * */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("classpath:/templates/");
//      templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /*
 * STEP 2 - Create SpringTemplateEngine
 * */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);

        templateEngine.addDialect(new LayoutDialect());
//       templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));

        return templateEngine;
    }

    /*
 * STEP 3 - Register ThymeleafViewResolver
 * */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
//        resolver.setContentType("text/html; charset=UTF-8");
        registry.viewResolver(resolver);
    }

    /*
     *  Dispatcher configuration for serving static resources
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

/*
    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("ru"));
        resolver.setCookieName("locale");
        resolver.setCookieMaxAge(60 * 60 * 24 * 365 * 10);
        return resolver;
    }
*/

    /*
    *  Message externalization/internationalization
    */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
/*
        messageSource.setBasename("classpath:/Messages");
        messageSource.setBasename("classpath:/validation");
*/
        messageSource.setBasenames("classpath:/Messages","classpath:/validation");
//        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultEncoding("Cp1251");
/*
        Properties properties =new Properties();
        properties.setProperty("WEB-INF/Messages","UTF-8");
        properties.setProperty("/Messages","UTF-8");
        messageSource.setFileEncodings(properties);
*/
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    //    Initialization for data source
//    server
/*
    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }
*/

    //    <!--delete after on realizae-->
//    test
    @Bean
    public DataSource dataSource() {

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:db/hsqldb/schema.sql")
                .addScript("classpath:db/hsqldb/data.sql")
                .build();

        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
//    		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//    		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("db/hsqldb/schema.sql").addScript("db/hsqldb/data.sql").build();
        //		EmbeddedDatabase db = builder.generateUniqueName(true).setType(EmbeddedDatabaseType.HSQL).addScript("db/hsqldb/schema.sql").addScript("db/hsqldb/data.sql").build();
//    		return db;

    }

    //    <!-- Initialization transaction manager, use JtaTransactionManager for global tx -->
    @Bean
    public PlatformTransactionManager txManager() throws NamingException {
/*
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
*/
        return new DataSourceTransactionManager(dataSource());

    }


    /*
        create the factory bean SqlSessionFactory
        In base MyBatis, the SqlSessionFactory is built using SqlSessionFactoryBuilder. In MyBatis-Spring, SqlSessionFactoryBean is used instead.
    */
    @Bean
//    @ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        Resource resource = new ClassPathResource("mybatis.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        sessionFactory.setConfigurationProperties(properties);
//        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/config/mybatis-config.xml"));

        return sessionFactory.getObject();
    }




/*
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
*/

    //default username : sa, password : ''
/*
	@PostConstruct
	public void getDbManager(){
	   DatabaseManagerSwing.main(
               new String[]{"--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
	}
*/

    // Apache Shiro Realm Configuration
    @Bean
    public Realm realm() {
        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(dataSource());
        String userRolesQuery = env.getProperty("shiro.userRolesQuery");
        realm.setUserRolesQuery(userRolesQuery);
        String authenticationQuery = env.getProperty("shiro.authenticationQuery");
        realm.setAuthenticationQuery(authenticationQuery);
        return realm;
//        return new MyCustomRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    //Apache Shiro filter Configuration
    //ShiroFilterFactoryBean - for 1.3.2
    //ShiroFilterChainDefinition - for 1.4.0
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition  = new DefaultShiroFilterChainDefinition();

//        chainDefinition .addPathDefinition("/roles", "authc");
//        filter.addPathDefinition("*", "anon");
//        chainDefinition .addPathDefinition("/**", "authcBasic");//basic auth
//        chainDefinition.addPathDefinition("/**", "anon"); // all paths are managed via annotations
        chainDefinition.addPathDefinition("/signin", "anon"); //  paths for login
        chainDefinition.addPathDefinition("/logout", "anon"); //  paths for login
        chainDefinition.addPathDefinition("/css/**", "anon"); //  paths for login
        chainDefinition.addPathDefinition("/js/**", "anon"); //  paths for login
        chainDefinition.addPathDefinition("/images/**", "anon"); //  paths for login
        chainDefinition.addPathDefinition("/fonts/**", "anon"); //  paths for login

        // or allow basic authentication, but NOT require it.
//        chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");

        // logged in users with the 'admin' role
//        chainDefinition.addPathDefinition("/roles/**", "authc, roles[admin]");
//        chainDefinition.addPathDefinition("/users/**", "authcBasic, roles[appadmin]");
//        chainDefinition.addPathDefinition("/roles/**", "authc, roles[admin]");
//        chainDefinition.addPathDefinition("/users/**", "authc, roles[appadmin]");
//        chainDefinition.addPathDefinition("/**", "authcBasic, roles[appadmin]");
        chainDefinition.addPathDefinition("/**", "authc, roles[appadmin]");
//        chainDefinition.addPathDefinition("/users/**", "authc, roles[appadmin]");

        // logged in users with the 'document:read' permission
//        chainDefinition.addPathDefinition("/users/**", "authc, perms[document:read]");

        // all other paths require a logged in user
//        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition ;
    }

}
