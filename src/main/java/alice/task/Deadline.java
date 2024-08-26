package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String line) throws InvalidTaskException {
        super(line);

        Map<String, String> flags = Parser.parseFlags(line);
        if (!flags.containsKey("by")) {
            throw new InvalidTaskException("Missing /by flag.");
        }
        try {
            this.by = Parser.parseDateString(flags.get("by"));
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
        keyValuePairs.add(String.format("\"by\": \"%s\"", Parser.toDateString(by)));
        return String.format("{%s}", String.join(", ", keyValuePairs));
    }

    public static Task fromJsonString(String jsonString) throws InvalidTaskException {
        Map<String, String> arguments = Parser.parseJsonString(jsonString);
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
