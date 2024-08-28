package bob.task;

import java.time.LocalDate;

public class ToDo extends Task {
    private static final String SYMBOL = "T";

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description) {
        this(description, false);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description;
    }

    public boolean isRelevant(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString();
    }
}
