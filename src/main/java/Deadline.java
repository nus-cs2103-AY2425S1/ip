public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) throws MurphyException {
        super(description);
        String byTrimmed = by.trim();
        if (byTrimmed.isEmpty()) {
            throw new MurphyException("Deadline by date cannot be empty!");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
