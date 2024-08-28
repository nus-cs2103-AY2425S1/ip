class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = deadline;
    }

    public Deadline(String taskDescription, String deadline) {
        this(taskDescription, deadline, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
