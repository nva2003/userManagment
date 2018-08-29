package rzd.pktbcki.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.io.Serializable;
import java.util.EnumSet;

/**
 * User: VNikishin
 * Date: 07.06.18
 * Time: 10:41
 */
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//public class MyWebApplicationInitializer implements WebApplicationInitializer {


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
private static final Logger logger = LoggerFactory.getLogger( MyWebApplicationInitializer.class );
*/
    
    /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/


    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/
    
    
    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { MyConfiguration.class };
    }

    /*
    If an application context hierarchy is not required, applications may return all configuration via
    getRootConfigClasses() and null from getServletConfigClasses().
    */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
//        return new Class<?>[] { App1Config.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }


    @Override
   	protected Filter[] getServletFilters() {

   		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
   		characterEncodingFilter.setEncoding("UTF-8");

        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilterFactoryBean");
        shiroFilter.setTargetFilterLifecycle(true);
//        shiroFilter.getFilterConfig().getServletContext().

//        FilterRegistration.Dynamic shiroFilter = container.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
//        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
//        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

//   		return new Filter[] { characterEncodingFilter};
   		return new Filter[] { characterEncodingFilter,shiroFilter};
   	}

/*
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "*/
/*");
    }
*/

}
