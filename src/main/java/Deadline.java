public class Deadline extends Task {
    private String deadlineTime;


    Deadline(String deadlineDesc, String deadlineTime) {
        super(deadlineDesc);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineTime + ")";
    }
}
