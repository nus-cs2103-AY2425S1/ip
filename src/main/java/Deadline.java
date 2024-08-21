public class Deadline extends Task {
    private final String endDateTime;

    public Deadline(String description, String endDateTime) {
        super(description);
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDateTime + ")";
    }
}
