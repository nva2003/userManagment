package rzd.pktbcki.login;

import java.util.HashMap;
import java.util.Map;

/**
 * User: VNikishin
 * Date: 21.08.18
 * Time: 14:29
 */
public enum PasswordInitial {

    IS_NOT_AN_INITIAL("1"), INSTALLED_ADMINISTRATOR("0"), EMBEDDED("2");

    private final String value;

    PasswordInitial(final String newValue) {
         value = newValue;
     }

     public String getValue() { return value; }

        // Mapping difficulty to difficulty id
    private static final Map<String, PasswordInitial> _map = new HashMap<String, PasswordInitial>();
    static
    {
        for (PasswordInitial difficulty : PasswordInitial.values())
            _map.put(difficulty.value, difficulty);
    }

}
