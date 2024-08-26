import java.time.LocalDateTime;

class Deadline extends Task {
    private LocalDateTime deadline;

    public static String format = "deadline <description> /by <date>";

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.toString() + ")";
    }
}
