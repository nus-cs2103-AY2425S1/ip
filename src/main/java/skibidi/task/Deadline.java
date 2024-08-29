package skibidi.task;

public class Deadline extends AbstractTask {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String marker, String description, String by) {
        super(marker, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String serialize() {
        return String.join("|", new String[]{"D", getStatusIcon(), description, by});
    }
}
