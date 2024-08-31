package tasks;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s | tasks.Deadline by %s", super.toString(), this.deadline);
    }

    public String getDeadline() {
        return deadline;
    }
}
