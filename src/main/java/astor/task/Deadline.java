package astor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String taskInfo, String deadline) {
        super(taskInfo);
        this.deadline = LocalDateTime.parse(generateParse(deadline));
    }

    public Deadline(String taskInfo, LocalDateTime deadline) {
        super(taskInfo);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String s = "[D] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return s;
    }

    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "D | " + i + " | " + this.getTaskInfo() + " | " + deadline;
    }
}
