package cypherchatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String str = this.completed ? "[D][X] " : "[D][ ] ";
        str += String.format("%s (by: %s)", this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        return str;
    }

    @Override
    public String toStringinFile() {
        int val = this.completed ? 1 : 0;
        return String.format("D|%d|%s|%s", val, this.description, this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
