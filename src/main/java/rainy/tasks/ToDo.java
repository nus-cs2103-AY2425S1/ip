package rainy.tasks;
import rainy.database.*;
import rainy.rainyexceptions.*;

/**
 * Represents a todo task. This class is a sub-class of the <code>Task</code> class.
 */
public class ToDo extends Task {
    /**
     * Constructs a new <code>ToDo</code> object.
     * @param name  Represents the name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Represents the task in a readable format.
     *{@code
     * ToDo todo = new ToDo("project meeting");
     * System.out.println(event);
     *
     * // Expected output:
     * // [T] borrow book
     * }
     * @return  Returns a string representing the <code>ToDo</code> object.
     */
    @Override
    public String toString() {
        return "[T] " + super.getName();
    }
}