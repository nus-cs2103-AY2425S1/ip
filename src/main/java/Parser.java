import java.util.HashMap;
import java.util.Map;

public class Parser {
    /**
     * Parses the input as a command.
     *
     * @param input The input to parse.
     * @return The command.
     */
    static public Command parse(String input) {
        String name;
        String param;
        Map<String, String> keywordParams = new HashMap<>();

        String[] commandAndParams = input.split(" +", 2); // split command and params
        name = commandAndParams[0];
        if (commandAndParams.length == 1) { // no params at all
            param = null;
        } else {
            String paramString = commandAndParams[1];
            String[] paramAndKeywordParams = paramString.split(" +(?=\\/)"); // split params
            param = paramAndKeywordParams[0]; // extract param
            for (int i = 1; i < paramAndKeywordParams.length; i++) { // extract keyword params
                String keywordParamString = paramAndKeywordParams[i];
                String[] keyAndValue = keywordParamString.split(" +", 2);  // split key and value
                keywordParams.put(keyAndValue[0].substring(1), keyAndValue[1]);
            }
        }

        return new Command(name, param, keywordParams);
    }
}

