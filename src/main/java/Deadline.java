public class Deadline extends Task {
    private static final String TYPE = "D";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + by + ")";
    }
    
    @Override
    public String toStorage() {
        return TYPE + "|" + super.toStorage() + "|" + by;
    }
}
