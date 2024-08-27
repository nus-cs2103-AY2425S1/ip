package Meeju;

public class Todo extends Task{
    public Todo(String taskDescription) {
        super(taskDescription, false);
    }

    private final String TASK_ICON = "[T]";

    public String serializeDetails() {
        return "T !- " + this.getIsDone() + "!- "
                + this.getTaskDescription() + "\n";
    }
    @Override
    public String toString() {
        return TASK_ICON + super.toString();
    }
}
