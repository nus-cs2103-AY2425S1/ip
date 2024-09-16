package darwin.task;

import darwin.exception.IllegalTaskTypeException;

/**
 * Enum to represent the different types of tasks.
 */
public enum TaskType {
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E");
    private final String name;
    private final String symbol;

    TaskType(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() { return this.symbol;}

    /**
     * Returns the TaskType from the given name.
     * @param name name of the TaskType represented as a string
     * @return TaskType
     * @throws IllegalTaskTypeException if the name is not a valid TaskType
     */
    public static TaskType fromName(String name) throws IllegalTaskTypeException {
        for (TaskType type : TaskType.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        // Allow to parse from symbol if name is not found
        return TaskType.fromSymbol(name);
    }

    /**
     * Returns the TaskType from the given symbol.
     * @param symbol symbol of the TaskType represented as a string
     * @return TaskType
     * @throws IllegalTaskTypeException if the symbol is not a valid TaskType
     */
    public static TaskType fromSymbol(String symbol) throws IllegalTaskTypeException {
        for (TaskType type : TaskType.values()) {
            if (type.getSymbol().equals(symbol)) {
                return type;
            }
        }
        throw new IllegalTaskTypeException(String.format("%s is not a valid task type", symbol));
    }
}
