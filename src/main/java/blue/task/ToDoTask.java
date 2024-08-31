package blue.task;

import java.util.Objects;

/**
 * TodoTask class that inherit from blue.task.Task.
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ToDoTask that = (ToDoTask) o;
        return Objects.equals(description, that.description) && this.isDone == that.isDone;
    }

    
}
