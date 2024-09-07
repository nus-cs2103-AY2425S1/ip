package mummy.command;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Parser class provides methods for parsing commands and extracting tokens from them.
 */
public final class Parser {

    /**
     * Parses a command string and extracts the command, description, and other arguments.
     *
     * @param command the command string to be parsed
     * @return a HashMap containing the parsed command, description, and other arguments
     */
    public static HashMap<String, String> parse(String command) {
        HashMap<String, String> tokens = new HashMap<>();

        // Define regex patterns
        Pattern commandPattern = Pattern.compile("^(\\w+)(\\s+[^/]+)?");
        Pattern argumentPattern = Pattern.compile("/(\\w+)\\s+([^/]+)");

        // Extract the command and description
        Matcher commandMatcher = commandPattern.matcher(command);
        if (commandMatcher.find()) {
            tokens.put("command", commandMatcher.group(1));
            if (commandMatcher.group(2) != null) {
                tokens.put("description", commandMatcher.group(2).trim());
            }
        }

        // Extract the tokens
        Matcher argumentMatcher = argumentPattern.matcher(command);
        while (argumentMatcher.find()) {
            tokens.put(argumentMatcher.group(1), argumentMatcher.group(2).trim());
        }

        return tokens;
    }

    /**
     * Parses the specified string into an integer or returns the default value if parsing fails.
     *
     * @param s the string to be parsed
     * @param defaultValue the default value to be returned if parsing fails
     * @return the parsed integer value or the default value if parsing fails
     */
    public static int parseIntOrDefault(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException exception) {
            return defaultValue;
        }
    }
}
