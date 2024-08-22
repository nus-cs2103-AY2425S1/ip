public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) throws DonnaException {
        super(description);
        if (by.trim().isEmpty()) {
            throw DonnaException.emptyDeadline();
        }
        this.by = by;
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