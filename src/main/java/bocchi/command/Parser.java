package bocchi.command;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class to parse string inputs to commands objects.
 */
public class Parser {
    /**
     * Parses the input as a command.
     *
     * @param input The input to parse.
     * @return The command.
     */
    public static Command parse(String input) {
        String name;
        String param;
        Map<String, String> keywordParams = new HashMap<>();

        String[] commandAndParams = input.split(" +", 2); // split command and params
        name = commandAndParams[0];
        if (commandAndParams.length == 1) { // no params at all
            param = null;
        } else {
            assert commandAndParams.length == 2 : "commandAndParams should have length either 1 or 2";

            String paramString = commandAndParams[1];
            String[] paramAndKeywordParams = paramString.split(" +(?=\\/)"); // split params

            assert paramAndKeywordParams.length >= 1 : "paramAndKeywordParams should have length >= 1";

            param = paramAndKeywordParams[0]; // extract param
            for (int i = 1; i < paramAndKeywordParams.length; i++) { // extract keyword params
                String keywordParamString = paramAndKeywordParams[i];
                String[] keyAndValue = keywordParamString.split(" +", 2); // split key and value

                assert keyAndValue.length >= 1 : "keyAndValue should have length >= 1";

                String key = keyAndValue[0].substring(1); // remove the leading '/'
                String value = keyAndValue.length == 2 ? keyAndValue[1] : null; // extract value if it is specified

                keywordParams.put(key, value);
            }
        }

        return new Command(name, param, keywordParams);
    }
}

