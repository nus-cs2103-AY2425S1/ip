public class Deadlines extends Task{
    private String by;

    /**
     * @param description The Description of the Deadline with /by to state the dateline
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
