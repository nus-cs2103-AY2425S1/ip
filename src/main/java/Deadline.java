public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String timeframe = String.format("(by: %s)", this.by);
        return String.format("[D]%s %s", super.toString(), timeframe);
    }
}