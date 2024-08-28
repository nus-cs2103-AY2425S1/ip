package bibi.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, String deadline) {
        // Call bibi.task.Task constructor
        super(task);

        // Parse the deadline appropriately
        if (deadline.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            LocalDate date = LocalDate.parse(deadline);
            this.deadline = String.format("%d %s %d",
                    date.getDayOfMonth(), date.getMonth().name(), date.getYear());
        } else {
            this.deadline = deadline;
        };

    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
