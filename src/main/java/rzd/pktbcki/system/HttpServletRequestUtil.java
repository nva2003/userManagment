package rzd.pktbcki.system;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * User: VNikishin
 * Date: 16.08.18
 * Time: 15:48
 */
public class HttpServletRequestUtil implements Serializable {


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
private static final Logger logger = LoggerFactory.getLogger( HttpServletRequestUtil.class );
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

    public static String getLogin (HttpServletRequest request) {
        String login = "undefined";

        if(request.getUserPrincipal() !=null && request.getUserPrincipal().getName() != null){
            login = request.getUserPrincipal().getName();
        }

        return login;
    }

    public static String getClientIp (HttpServletRequest request) {
        return getIp(request);
    }

    private static String getIp(HttpServletRequest request) {

           String remoteAddr = "undefined";

           if (request != null) {
               remoteAddr = request.getHeader("X-FORWARDED-FOR");
               if (remoteAddr == null || "".equals(remoteAddr)) {
                   remoteAddr = request.getRemoteAddr();
               }
           }

           return remoteAddr;
       }

    private static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR" };

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
