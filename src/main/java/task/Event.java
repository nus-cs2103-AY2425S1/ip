package task;

import java.util.*;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String line) throws InvalidTaskException {
        super(line);

        Map<String, String> flags = parseFlags(line);
        if (!flags.containsKey("from")) {
            throw new InvalidTaskException("Missing /from flag.");
        }
        if (!flags.containsKey("to")) {
            throw new InvalidTaskException("Missing /to flag.");
        }
        this.from = flags.get("from");
        this.to = flags.get("to");
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), from, to);
    }
}
