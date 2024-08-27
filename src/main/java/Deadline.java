public class Deadline extends Task {

    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String getState() {
        return String.format("D | %s | %s", super.getState(), this.by);
    }
}
