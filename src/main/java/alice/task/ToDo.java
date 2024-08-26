package alice.task;

import java.util.*;

public class ToDo extends Task {
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
        return String.format("{%s}", String.join(", ", keyValuePairs));
    }

    public static Task fromJsonString(String jsonString) throws InvalidTaskException {
        Map<String, String> arguments = Parser.parseJsonString(jsonString);
        String inputLine = String.format("toDo %s", arguments.get("description"));
        ToDo toDo = new ToDo(inputLine);
        toDo.isCompleted = arguments.get("isCompleted").compareTo("true") == 0;
        return toDo;
    }
}
