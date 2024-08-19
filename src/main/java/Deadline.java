public class Deadline extends Task{
    private String by;

    public Deadline(String taskDescription, String taskDeadline){
        super(taskDescription, false);
        this.by = taskDeadline;
    }

    private final String TASK_ICON = "[D]";

    @Override
    public String toString() {
        return TASK_ICON + super.toString() + " (by: " + this.by + ")";
    }
}
