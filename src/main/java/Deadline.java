public class Deadline extends Task {
    private final String by;
    private final String description;

    public Deadline(String description, String by) throws DonnaException {
        super(description);
        if (by.trim().isEmpty()) {
            throw DonnaException.emptyDeadline();
        }
        this.by = by;
        this.description = description;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isDone() ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() // type, status & desc
                + "(by: " + by + ")";
    }
}