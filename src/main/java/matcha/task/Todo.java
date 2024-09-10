package matcha.task;

/**
 * Represents a todo task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task given a description.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task when saving to a file.
     *
     * @return String representation of the Todo task when saving to a file.
     */
    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

    /**
     * Returns the string representation of the Todo task when printing to the user.
     *
     * @return String representation of the Todo task when printing to the user.
     */
    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }
    /**
     * Checks if the Todo is a duplicate of another Todo.
     *
     * @param task The task to compare with.
     * @return Returns true if the task is a duplicate. Otherwise, returns false.
     */
    @Override
    public boolean checkDuplicate(Task task) {
        int taskIconLength = 8;
        if (!(task instanceof Todo)) {
            return false;
        }
        if (task == this) {
            return true;
        }
        //get the substring of the task description without the status icon
        String taskSubstring = this.toString().substring(taskIconLength);
        String otherTaskSubstring = task.toString().substring(taskIconLength);
        return taskSubstring.equals(otherTaskSubstring);
    }
}

