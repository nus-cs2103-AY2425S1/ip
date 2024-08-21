package task;

import java.util.*;

public class Deadline extends Task {
    private String by;

    public Deadline(String line) {
        super(line);

        Map<String, String> flags = parseFlags(line);
        this.by = flags.get("by");
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
