public class Deadline extends Task {
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // todo: use java LocalDateTime
    private String deadline;

    public Deadline(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = "None";
    }
    public Deadline(String taskName, boolean taskDone, String deadline) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s, Deadline: %s", super.toString(), this.deadline);
    }
}

