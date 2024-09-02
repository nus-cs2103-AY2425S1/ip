package juno.task;

/**
 * Represents an Todo task type with a description.
 * Subclass of Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo instance which takes in a specified description and task type.
     * Calls its superclass Task.
     *
     * @param description The description of the deadline.
     * @param taskType The type of the task, i.e. "deadline".
     *
     */
    public Todo(String description, String taskType) {
        super(description, taskType);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[📝 ToDo] " + super.toString() + " - Let's get this done! 💪";
    }
}
