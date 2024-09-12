package blob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a Task that has a deadline set.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String name, boolean isDone, String deadline, ArrayList<String> tags) {
        super(name, isDone, tags);
        this.deadline = LocalDateTime.parse(deadline);
        assert this.deadline.isAfter(LocalDateTime.now()) : "Deadline of task is already in the past!";
        super.type = "D";
    }

    /**
     * @return String representation of the deadline task in the form
     * "[D] ['completion status] 'task name' (by: 'MMM d HH:mm')"
     */
    @Override
    public String toString() {
        if (this.tags.isEmpty()) {
            return "[D]" + "[" + this.check() + "] " + this.name +
                    " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ")";
        }
        StringBuilder tags = new StringBuilder("");
        for (int i = 0; i < this.tags.size(); i++) {
            String tag = this.tags.get(i);
            tags.append("#" + tag + " ");
        }
        return "[D]" + "[" + this.check() + "] " + this.name +
                " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ") - " +
                tags.toString();
    }

    /**
     * @return LocalDateTime of the tasks deadline
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
