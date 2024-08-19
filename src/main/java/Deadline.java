public class Deadline extends Task {
    String endTime;

    public Deadline(String taskName, String endTime) {
        super(taskName);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }
}
