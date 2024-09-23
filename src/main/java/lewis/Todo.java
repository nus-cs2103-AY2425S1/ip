package lewis;

/**
 * This class implements a todo,
 * which can capture a description of this todo,
 * and its completion status
 */
public class Todo extends Task {
    /**
     * Protected constructor for a todo.
     * This should be used to create a new Todo.
     * By default, its completion status is NOT_DONE
     * @param description a description of this todo
     */
    protected Todo(String description) {
        super(description);
    }

    /**
     * Protected constructor for a todo.
     * This should be used to load a todo from storage.
     * @param description a string representation of this todo
     * @param status a string representation of this todo's completion status
     */
    protected Todo(String description, String status) {
        super(description, Status.valueOf(status));
    }

    /**
     * Returns a string representation of this todo
     * @return a string
     */
    @Override
    public String toString() {
        return "[T]"
                + super.toString();
    }

    /**
     * Returns a csv representation of this todo.
     * @return A string in the form "Todo,(task description),(task status)"
     */
    @Override
    protected String toCsv() {
        return "Todo,"
                + super.toCsv();
    }
}
