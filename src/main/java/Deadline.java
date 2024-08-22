public class Deadline extends Task {

    protected String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[⏰ Deadline] " + super.toString() + " - Don't miss it! ⏳ (due: " + endTime + ")";
    }
}

