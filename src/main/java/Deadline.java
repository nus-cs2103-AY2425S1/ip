public class Deadline extends Task {
    private final String byTime;

    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byTime + ")";
    }
}
