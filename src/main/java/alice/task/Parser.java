package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Handles input logic. */
public class Parser {
    private static final DateTimeFormatter[] DATETIME_FORMATTERS = new DateTimeFormatter[]{
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
    };

    /**
     * Parses the description argument in a command.
     * This refers to the text after the command verb
     * but before the first slash flag argument.
     * 
     * @param  line                     the input line from the user
     * @throws IllegalArgumentException if the input line is not well-formed
     * @return                          the description
     */
    public static String parseDescription(String line) throws IllegalArgumentException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new IllegalArgumentException();
        }
        String arguments = tokens[1].trim();
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

    /**
     * Parses a datetime in yyyy-MM-dd HH:mm, yyyy-MM-dd HHmm,
     * dd-MM-yyyy HH:mm or dd-MM-yyyy HHmm format.
     * 
     * @param  dateString             the date string to parse
     * @throws DateTimeParseException if the date string is not well=formed
     * @return                        the parsed LocalDateTime
     */
    protected static LocalDateTime parseDateString(String dateString) throws DateTimeParseException {
        for (DateTimeFormatter formatter :  DATETIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException exception) {
                // try another format
            }
        }
        return LocalDateTime.parse(dateString);
    }

    /**
     * Converts a datetime to string 
     * in yyyy-MM-dd HH:mm format.
     * 
     * @param  datetime the datetime to convert
     * @return          the formatted datetime string
     */
    protected static String toDateString(LocalDateTime datetime) {
        return DATETIME_FORMATTERS[0].format(datetime);
    }
}