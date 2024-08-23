public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "%s (by: %s)".formatted(super.toString(), by);
    }
}
