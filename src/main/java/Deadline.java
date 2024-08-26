public class Deadline extends Task {
    private final String byTime;

    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
    }

    public String getByTime() {
        return this.byTime;
    }

    @Override
    public String type() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byTime + ")";
    }
}
