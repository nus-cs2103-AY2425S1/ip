package task;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

public abstract class Task {
    private static final DateTimeFormatter[] DATETIME_FORMATTERS = new DateTimeFormatter[]{
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
    };

    protected String description;
    protected boolean isCompleted;

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String line) throws InvalidTaskException {
        this.description = parseDescription(line);
        this.isCompleted = false;
    }

    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    private static String parseDescription(String line) throws InvalidTaskException {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            throw new InvalidTaskException();
        }
        String arguments = tokens[1].trim();
        return arguments.split("/", 2)[0].trim();
    }

    protected static Map<String, String> parseFlags(String line) {
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

    protected static Map<String, String> parseJsonString(String jsonString) {
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

    public static Task fromJsonString(String jsonString) throws InvalidTaskException {
        Map<String, String> arguments = parseJsonString(jsonString);        
        switch (TaskType.valueOf(arguments.get("taskType").toUpperCase())) {
            case TODO:
                return ToDo.fromJsonString(jsonString);
            case DEADLINE:
                return Deadline.fromJsonString(jsonString);
            case EVENT:
                return Event.fromJsonString(jsonString);
            default:
                throw new InvalidTaskException();
        }
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

    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
    }

    public abstract String toJsonString();
}
