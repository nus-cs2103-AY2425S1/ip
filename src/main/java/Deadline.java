public class Deadline extends Task {

    protected String endTime;

    public Deadline(String description, String endTime, String taskType) {
        super(description, taskType);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[⏰ Deadline] " + super.toString() + " - Don't miss it! ⏳ (due: " + endTime + ")";
    }

    public String getEndTime() {
        return this.endTime;
    }
}

