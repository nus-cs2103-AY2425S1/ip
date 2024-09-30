package killjoy.task;

/**
 * Represents the Todo class of the KillJoy application.
 * Contains methods to create and manage Todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getTaskInfo() {
        return "TODO|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
