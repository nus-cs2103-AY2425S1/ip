public class Deadline extends Task{

    protected String byTime;

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X]" : "[ ]") + this.description + "(by:" + this.byTime + ")";
    }
}
