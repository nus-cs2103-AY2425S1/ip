package luffy;

/**
 * Represents a task that has a deadline
 */
public class Deadline extends Task {

    private final String deadline;

    public Deadline(String taskInfo, String deadline, boolean isDone) {
        super(taskInfo, isDone);
        this.deadline = deadline;
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[D][X] %s (by: %s)", super.getTaskInfo(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)", super.getTaskInfo(), this.deadline);
        }
    }

    @Override
    public String dataToSave() {
        if (checkIsDone()) {
            return String.format("DEADLINE      | 1 | %s | BY: %s", super.getTaskInfo(), this.deadline);
        } else {
            return String.format("DEADLINE      | 0 | %s | BY: %s", super.getTaskInfo(), this.deadline);
        }
    }

}