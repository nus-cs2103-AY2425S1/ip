public class Deadline extends Task {
    public String deadlineTiming;

    private Deadline(String taskDescription, String deadlineTiming, boolean isDone) {
        super(taskDescription, isDone);
        this.deadlineTiming = deadlineTiming;
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.deadlineTiming);
    }

    public static Deadline createDeadline(String taskDescription, String deadlineTiming, boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved deadline task has no task description!");
        } else if (deadlineTiming.isBlank()) {
            throw new NedException("M'lord, this saved deadline task has no due date!");
        };
        return new Deadline(taskDescription, deadlineTiming, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("deadline|%d|%s", status, this.taskDescription);
    }
}