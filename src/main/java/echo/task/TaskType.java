package echo.task;
/**
 * The TaskType enum represents the different types of tasks a user can add to TaskList.
 * Each type has an associated symbol used for identification.
 */
public enum TaskType {
    /**
     * Represents a task that is a simple to-do item.
     */
    TODO("T"),
    /**
     * Represents a task that has a deadline.
     */
    DEADLINE("D"),
    /**
     * Represents a task that is an event with a start and end time.
     */
    EVENT("E");
    private String symbol;
    /**
     * Constructs a TaskType with the specified symbol.
     *
     * @param symbol the symbol representing the task type
     */
    private TaskType(String symbol) {
        this.symbol = symbol;
    }
    /**
     * Returns the symbol associated with the task type.
     *
     * @return the symbol representing the task type
     */
    public String getTypeSymbol() {
        return this.symbol;
    }
}
