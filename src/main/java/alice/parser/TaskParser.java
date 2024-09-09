package alice.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Handles task command inputs. */
public class TaskParser {
    /**
     * Parses the description argument in a command.
     * This refers to the text after the command verb
     * but before the first slash flag argument.
     *
     * @param  line                     the input line from the user
     * @return                          the description
     * @throws IllegalArgumentException if the input line is not well-formed
     */
    public static String parseDescription(String line) throws IllegalArgumentException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new IllegalArgumentException();
        }
        String arguments = tokens[1].trim();
        assert arguments.split("/", 2).length > 0;
        return arguments.split("/", 2)[0].trim();
    }

    /**
     * Parses the slash flag arguments.
     * Each flag has a key and a value.
     *
     * @param  line the input line from the user
     * @return      the map of key-value pairs for each argument
     */
    public static Map<String, String> parseFlags(String line) {
        String[] tokens = line.split("/");
        Map<String, String> arguments = new HashMap<>();
        for (int i = 1; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
            int splitIndex = tokens[i].indexOf(" ");
            if (splitIndex == -1) {
                continue;
            }

            String argumentName = tokens[i].substring(0, splitIndex).trim();
            String argumentValue = tokens[i].substring(splitIndex + 1).trim();
            arguments.put(argumentName, argumentValue);
        }
        return arguments;
    }

    /**
     * Parses the key-value pairs from a JSON string.
     *
     * @param  jsonString the JSON string to parse
     * @return            the map of key-value pairs for each argument
     */
    public static Map<String, String> parseJsonString(String jsonString) {
        Map<String, String> arguments = new HashMap<>();
        Pattern keyValuePairPattern = Pattern.compile("(\\\"\\w+\\\"): (\\\"[\\w\\s:-]+\\\")");
        Matcher matcher = keyValuePairPattern.matcher(jsonString);
        while (matcher.find()) {
            // remove string quotes from json
            String key = matcher.group(1).replaceAll("\"", "");
            String value = matcher.group(2).replaceAll("\"", "");
            arguments.put(key, value);
        }
        return arguments;
    }
}
