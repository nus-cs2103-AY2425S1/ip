package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alice.parser.DateParser;
import alice.parser.TaskParser;

/** Task with a /by field. */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a deadline.
     *
     * @throws InvalidTaskException if the input line is invalid
     */
    public Deadline(String line) throws InvalidTaskException {
        super(line);

        Map<String, String> flags = TaskParser.parseFlags(line);
        if (!flags.containsKey("by")) {
            throw new InvalidTaskException("Missing /by flag.");
        }

        try {
            this.by = DateParser.parseDateString(flags.get("by"));
        } catch (DateTimeParseException exception) {
            throw new InvalidTaskException("Invalid /by datetime. Should be yyyy-mm-dd hh:mm`");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toJsonString() {
        List<String> keyValuePairs = new ArrayList<>();
        keyValuePairs.add("\"taskType\": \"deadline\"");
        keyValuePairs.add(String.format("\"description\": \"%s\"", description));
        keyValuePairs.add(String.format("\"isCompleted\": \"%s\"", isCompleted));
        keyValuePairs.add(String.format("\"by\": \"%s\"", DateParser.toDateString(by)));
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
        assert arguments.containsKey("by");

        String inputLine = String.format(
            "deadline %s /by %s",
            arguments.get("description"),
            arguments.get("by")
        );
        Deadline deadline = new Deadline(inputLine);
        deadline.isCompleted = arguments.get("isCompleted").compareTo("true") == 0;
        return deadline;
    }
}
