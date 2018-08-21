package rzd.pktbcki.login;

import java.util.HashMap;
import java.util.Map;

/**
 * User: VNikishin
 * Date: 21.08.18
 * Time: 14:29
 */
public enum PasswordState {

    CHANGE_IS_NECESSARY(1), POSSIBLE_TO_CHANGE(2),IMPOSSIBLE_TO_CHANGE(3);

    private final int value;

    PasswordState(final int newValue) {
         value = newValue;
     }

     public int getValue() { return value; }

        // Mapping difficulty to difficulty id
    private static final Map<Integer, PasswordState> _map = new HashMap<Integer, PasswordState>();
    static
    {
        for (PasswordState difficulty : PasswordState.values())
            _map.put(difficulty.value, difficulty);
    }

}
