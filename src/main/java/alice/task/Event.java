package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alice.parser.DateParser;
import alice.parser.TaskParser;

/** Task with /from and /to fields. */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an event.
     *
     * @throws InvalidTaskException if the input line is invalid`
     */
    public Event(String line) throws InvalidTaskException {
        super(line);

        Map<String, String> flags = TaskParser.parseFlags(line);
        if (!flags.containsKey("from")) {
            throw new InvalidTaskException("Missing /from flag.");
        }
        if (!flags.containsKey("to")) {
            throw new InvalidTaskException("Missing /to flag.");
        }

        try {
            this.from = DateParser.parseDateString(flags.get("from"));
        } catch (DateTimeParseException exception) {
            throw new InvalidTaskException("Invalid /from datetime. Should be yyyy-mm-dd hh:mm`");
        }
        try {
            this.to = DateParser.parseDateString(flags.get("to"));
        } catch (DateTimeParseException exception) {
            throw new InvalidTaskException("Invalid /to datetime. Should be yyyy-mm-dd hh:mm`");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), from, to);
    }

    @Override
    public String toJsonString() {
        List<String> keyValuePairs = new ArrayList<>();
        keyValuePairs.add("\"taskType\": \"event\"");
        keyValuePairs.add(String.format("\"description\": \"%s\"", description));
        keyValuePairs.add(String.format("\"isCompleted\": \"%s\"", isCompleted));
        keyValuePairs.add(String.format("\"from\": \"%s\"", DateParser.toDateString(from)));
        keyValuePairs.add(String.format("\"to\": \"%s\"", DateParser.toDateString(from)));
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
        assert arguments.containsKey("from");
        assert arguments.containsKey("to");

        String inputLine = String.format(
            "event %s /from %s /to %s",
            arguments.get("description"),
            arguments.get("from"),
            arguments.get("to")
        );
        Event event = new Event(inputLine);
        event.isCompleted = arguments.get("isCompleted").compareTo("true") == 0;
        return event;
    }
}
