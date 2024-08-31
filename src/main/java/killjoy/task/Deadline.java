package killjoy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getTaskInfo() {
        return "DEADLINE|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|"
                + String.valueOf(this.by).replace("T", " ") + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(formatter) + ")";
    }

}
