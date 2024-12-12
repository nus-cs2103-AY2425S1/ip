package duch.task;

/**
 * Encapsulates a Todo task.
 */
public class Todo extends Task {

    public Todo(String task, boolean done) {
        super(task, done);
    }
    
    public Todo(String task) {
        super(task);
    }

    /**
     * Converts the todo object to a string to be printed.
     * 
     * @return The string.
     */
    public String toString() {
        return (isDone) ? "[T][X] " + task : "[T][ ] " + task;
    }

    /**
     * Converts the todo object to a string for storage.
     * 
     * @return The string.
     */
    public String toStore() {
        return (isDone) ? "T|1|" + task : "T|0|" + task;
    }
}
