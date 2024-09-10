package katheryne;

/**
 * ToDo Class is a child class of Task Class.
 * It has same attributes as the Task Class.
 */
public class ToDo extends Task {
    public static final String TYPE = "Todo";

    public ToDo(String description) {
        super(description, TYPE);
    }

    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        if (isDone) {
            return String.format("T | %d | %s", 1, this.getDescription());
        } else {
            return String.format("T | %d | %s", 0, this.getDescription());
        }
    }

    public String getType() {
        return this.type;
    }
    @Override
    public boolean equals(Task t) {
        if (super.equals(t)) {
            ToDo todo = (ToDo) t;
            return this.getDescription().equals(todo.getDescription());
        }
        return false;
    }
}
