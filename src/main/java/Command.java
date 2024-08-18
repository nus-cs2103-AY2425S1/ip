import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Command {
    /**
     * The command name.
     */
    private final String name;

    /**
     * The parameter.
     */
    private final String param;

    /**
     * Optional keyword parameters specified using '/key value' format.
     */
    private final Map<String, String> keywordParams = new HashMap<>();

    /**
     * The constructor. Splits input into command and paramString.
     *
     * @param input A full line of command together with all parameters.
     */
    public Command(String input) {
        String[] commandAndParams = input.split(" +", 2); // split command and params
        name = commandAndParams[0];
        if (commandAndParams.length == 1) { // no params at all
            param = "";
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
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parameter string.
     *
     * @return The parameter string.
     */
    public String getParam() {
        return param;
    }

    /**
     * Gets the keyword parameter value associated with key.
     * @param key The key to the required keyword parameter.
     * @return The value of the required keyword parameter.
     */
    public String getKeywordParam(String key) {
        return keywordParams.get(key);
    }

    public static void main(String[] args) {
        new Command("event  project  meeting    /from    Mon    2pm    /to       4pm");
    }

}
