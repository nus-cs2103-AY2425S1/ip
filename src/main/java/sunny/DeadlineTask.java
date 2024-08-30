package sunny;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represent tasks with deadlines
 */
public class DeadlineTask extends Task {
    public DeadlineTask(String message) {
        super(message);
    }
    String m1 = super.name.split("/by ", 2)[0];
    String deadline = super.name.split("/by ", 2)[1];
    LocalDate d = LocalDate.parse(deadline.trim());

    @Override
    public String getTimeline() {
        return deadline;
    }
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[D][X] %s (by: %s)", m1, deadline);
        } else {
            return String.format("[D][] %s (by: %s)", m1, deadline);
        }
    }
}
