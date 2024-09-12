package tasks;

/**
 * Represents an Event task.
 */
public class ToDo extends Task {
    /**
     * Initialisation of a ToDo task with the required attributes.
     * @param name Name of the task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string on how an Event should be stored in a save file.
     * @return Save format for the task.
     */
    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        } else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("todo ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    /**
     * Returns the string representation of Todo.
     * @return String representation of Todo
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
