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

    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString();
    }
}
