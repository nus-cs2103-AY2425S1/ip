public class Deadline extends Task {
    private String endTime;

    public Deadline(String desc, String endTime, boolean isDone) {
        super(desc, isDone);
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "D" + super.getSaveTaskString() + "|" + this.endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endTime + ")";
    }
}
