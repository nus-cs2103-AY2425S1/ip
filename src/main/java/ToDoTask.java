/**
 * TodoTask class that inherit from Task.
 * Basically a kind of task that only has a description
 */

public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /** Returns string that will be written into text file*/
    @Override
    public String toFileString() {
        return "T | " + getStatusIcon() + " | " + getDescription();
    }

    
}
