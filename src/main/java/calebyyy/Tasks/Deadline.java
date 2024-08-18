package calebyyy.Tasks;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() throws invalidArgumentException {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}