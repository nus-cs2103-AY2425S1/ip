package alice.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import alice.parser.TaskParser;

/** Basic task with no time constraints. */
public class ToDo extends Task {
    /**
     * Creates a to-do and marks it as incomplete.
     *
     * @throws InvalidTaskException if the input line is invalid
     */
    public ToDo(String line) throws InvalidTaskException {
        super(line);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toJsonString() {
        List<String> keyValuePairs = new ArrayList<>();
        keyValuePairs.add("\"taskType\": \"toDo\"");
        keyValuePairs.add(String.format("\"description\": \"%s\"", description));
        keyValuePairs.add(String.format("\"isCompleted\": \"%s\"", isCompleted));
        keyValuePairs.add(String.format("\"tags\": \"[%s]\"", String.join(",", tags)));
        return String.format("{%s}", String.join(", ", keyValuePairs));
    }

    /**
     * Creates a task from a given JSON string.
     *
     * @param jsonString input string
     */
    public static Task fromJsonString(String jsonString) throws InvalidTaskException {
        Map<String, String> arguments = TaskParser.parseJsonString(jsonString);
        assert arguments.containsKey("description");

        String inputLine = String.format("toDo %s", arguments.get("description"));
        ToDo toDo = new ToDo(inputLine);
        toDo.isCompleted = arguments.get("isCompleted").compareTo("true") == 0;
        String tagsArraysString = arguments.getOrDefault("tags", "");
        if (tagsArraysString.length() > 2) {
            Stream.of(tagsArraysString.substring(1, tagsArraysString.length() - 1).split(","))
                    .forEach((tag) -> toDo.addTag(tag));
        }
        return toDo;
    }
}
