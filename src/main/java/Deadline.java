public class Deadline extends Task {
    private final String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + " (by: " + end + ")";
    }
}
