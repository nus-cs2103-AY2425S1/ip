package bocchi.command;

import java.util.List;
import java.util.Map;

/**
 * Represents a command that the user inputs.
 */
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
    private final Map<String, String> keywordParams;

    /**
     * The constructor.
     *
     * @param name          The name of the command.
     * @param param         The parameter of the command.
     * @param keywordParams The keyword parameters of the command.
     */
    public Command(String name, String param, Map<String, String> keywordParams) {
        this.name = name;
        this.param = param;
        this.keywordParams = keywordParams;
    }

    public String getName() {
        return name;
    }


    public String getParam() {
        return param;
    }

    /**
     * Gets the keyword parameter value associated with key.
     *
     * @param key The key to the required keyword parameter.
     * @return The value of the required keyword parameter.
     */
    public String getKeywordParam(String key) {
        return keywordParams.get(key);
    }

    /**
     * Gets the values of a list-like keyword parameter associated with key.
     *
     * @param key The key to the required keyword parameter.
     * @return The list of values of the required keyword parameter.
     */
    public List<String> getListKeywordParam(String key) {
        if (!keywordParams.containsKey(key)) {
            return null;
        } else if (keywordParams.get(key) == null) {
            return null;
        }
        return List.of(getKeywordParam(key).split(" +"));
    }
}
