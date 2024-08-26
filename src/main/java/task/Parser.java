package task;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

public class Parser {
    private static final DateTimeFormatter[] DATETIME_FORMATTERS = new DateTimeFormatter[]{
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
    };

    public static String parseDescription(String line) throws IllegalArgumentException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new IllegalArgumentException();
        }
        String arguments = tokens[1].trim();
        return arguments.split("/", 2)[0].trim();
    }

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

    protected static String toDateString(LocalDateTime datetime) {
        return DATETIME_FORMATTERS[0].format(datetime);
    }
}