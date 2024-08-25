package task;

import exception.IllegalTaskTypeException;

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
    public static TaskType fromName(String name) throws IllegalTaskTypeException {
        for (TaskType type : TaskType.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalTaskTypeException();
    }
    public static TaskType fromSymbol(String symbol) throws IllegalTaskTypeException {
        for (TaskType type : TaskType.values()) {
            if (type.getSymbol().equals(symbol)) {
                return type;
            }
        }
        throw new IllegalTaskTypeException();
    }
}
