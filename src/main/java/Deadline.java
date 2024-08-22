public class Deadline extends Task{
    protected String taskType = "D";
    protected String dt;

    public Deadline(String description, String dt) {
        super(description);
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (due: " + this.dt + ")";
    }
}
