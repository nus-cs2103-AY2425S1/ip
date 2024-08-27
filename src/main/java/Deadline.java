public class Deadline extends Task {
    private static final String SYMBOL = "D";
    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, String by) {
        this(description, false, by);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description + "," + by;
    }

    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString() + " (by: " + by + ")";
    }
}
