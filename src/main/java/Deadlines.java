public class Deadlines extends Task {
    protected String endTime;
    public Deadlines(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }
}
