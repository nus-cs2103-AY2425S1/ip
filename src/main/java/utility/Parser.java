package utility;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
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
}
