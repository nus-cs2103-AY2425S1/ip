package nen.tasks;

/**
 * This class represents a Todo Task
 * @author Gan Ren Yick (A0276246X)
 */

public class Todo extends Task {

    /**
     * Constructs a Todo task
     * @param description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T/" + this.isDone + "/" + this.description;
    }
}
