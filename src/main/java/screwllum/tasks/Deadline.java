package screwllum.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(formatter));
    }

    public String toSaveFormat() {
        return String.format("D_%s_%s_%s", isDone ? "1" : "0", getDesc(), deadline.format(formatter));
    }
}
