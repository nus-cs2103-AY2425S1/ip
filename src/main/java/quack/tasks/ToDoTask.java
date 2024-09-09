package quack.tasks;

/**
 * This class defines and prodides functionality for todo tasks.
 */
public class ToDoTask extends Task {

    /**
     * Creates a ToDo task object with the given description.
     * @param taskName Description of the task.
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toCsvFormat() {
        return "TODO," + super.toCsvFormat() + "," + this.isChecked;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
