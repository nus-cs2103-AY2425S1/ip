package reo.tasks;

import reo.tasks.Task;

/** Represents the Task type Todo */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     *
     * @param name The name of the deadline task.
     * @param isDone The completion status of the deadline task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * toString method for the Deadline class.
     *
     * @return The string representation of the event to be displayed to user.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns the line to be written to the file to represent the Todo object.
     *
     * @return The string representation of the Todo to be written to memory.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
