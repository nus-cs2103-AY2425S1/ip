public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        type = TaskType.DEADLINE;
    }

    public static Deadline fromTaskString(String taskString) {
        String[] parts = taskString.split("\\|");
        if (parts.length < 4)
            return null;

        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1"))
            deadline.markAsDone();

        return deadline;
    }

    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toTaskString() {
        return String.format("%s|%d|%s|%s",
                getTaskType(), isDone ? 1 : 0, description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", getTaskType(),
                super.toString(), deadline);
    }
}
