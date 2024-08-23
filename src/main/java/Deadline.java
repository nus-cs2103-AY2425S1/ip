public class Deadline extends Task {
    protected String by;
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs new Deadline task by extracting /by from the String
     *
     * @param str Un-separated String
     */
    public Deadline (String str) {
        this(str.substring(0, str.toLowerCase().indexOf("/by ")),
                str.substring(str.toLowerCase().indexOf("/by ") + 4, str.length()));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
