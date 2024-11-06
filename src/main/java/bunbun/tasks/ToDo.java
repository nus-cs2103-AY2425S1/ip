package bunbun.tasks;

/**
 * This class implements a ToDo task.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class ToDo extends Task {

    /**
     * Instantiates a To Do task.
     *
     * @param task String description of task.
     */
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String genFileString() {
        String isComplete = (this.isComplete()) ? "true" : "false";
        String taskDescription = String.format("%s;%s;%s\n", "todo", isComplete, this.getTask());
        return taskDescription;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
