package rzd.pktbcki.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * User: VNikishin
 * Date: 02.08.18
 * Time: 17:44
 */
public class StringToTimestampPropertyEditorSupport extends
        PropertyEditorSupport {


    /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    private static final long serialVersionUID = 1L;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
private static final Logger logger = LoggerFactory.getLogger(StringToTimestampPropertyEditorSupport.class);

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
    public void setAsText(String text) {
        logger.debug("Has it come to this ? (StringToTimestampPropertyEditorSupport):" + text );

        try {
            setValue(new java.sql.Timestamp(sdf.parse(text).getTime()));
        } catch (ParseException e) {
            e.printStackTrace(); //yobadubudu
        }
    }
}
