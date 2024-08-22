public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws EmptyDescriptionException, EmptyDeadlineException {
        super(description);
        this.by = by;
        if (description == null) {
            throw new EmptyDescriptionException("Description of deadline cannot be empty.");
        }
        if (by == null) {
            throw new EmptyDeadlineException("Deadline cannot be empty");
        }
    }

    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + super.toString() + " (by: " + by + ")";
    }
}
