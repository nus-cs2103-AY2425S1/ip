package socchat.task.todo;

import socchat.task.Task;

/**
 * Represents a 'Todo' task, which is a type of task with a description and a completion status.
 * Inherits from the {@link Task} class and provides additional functionality specific to 'Todo' tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new 'Todo' task with the specified description.
     * The task is initially set as not done.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new 'Todo' task with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the 'Todo' task for saving to a file.
     * The format of the string is "T | [Done/Not done] | description".
     *
     * @return a string representation of the 'Todo' task suitable for saving
     */
    public String toSave() {
        return "T" + " | " + super.getDoneStatus() + " | " + super.getDescription();
    }
}
