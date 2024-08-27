package blob;

/**
 * Represents a 'ToDo' task.
 * Constructor requires name and boolean value of whether task is done or not.
 */
public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name,isDone);
        super.type = "T";
    }

    /**
     * @return string representation of the task in the form "[T] ['completion status'] 'task name'".
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.check() + "] " + this.name;
    }
}

