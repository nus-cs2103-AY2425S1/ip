public class Deadline extends Task{
    private String by;

    public Deadline(String taskDescription, String taskDeadline){
        super(taskDescription, false);
        this.by = taskDeadline;
    }

    private final String TASK_ICON = "[D]";

    public String getDeadline() {
        return this.by;
    }

    @Override
    public String serializeDetails() {
        return "D !- " + this.getIsDone() + "!- "
                + this.getTaskDescription() + "!- "
                + this.getDeadline() + "\n";
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString() + " (by: " + this.by + ")";
    }
}
