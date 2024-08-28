public class Deadline extends Task {

    private String deadline;
    private Task task;

    public Deadline(String description, String deadline) {
        super(description);
        this.task = new Task(this.description);
        this.deadline = deadline.replaceFirst("by ", "");
    }

    @Override
    public String markTask() {
        return String.format("[D] %s (by: %s)", this.task.markTask(), this.deadline);
    }

    @Override
    public String unmarkTask() {
        return String.format("[D] %s (by: %s)", this.task.unmarkTask(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", this.task.toString(), this.deadline);
    }

}
