package Task;

public class DeadlineTask extends Task{
    private String by;

    public DeadlineTask(String eventName, String by, boolean completed) {
        super(eventName, completed);
        this.by = by;
    }
    @Override
    public String toCacheString() {
        return "D|" + (this.isCompleted() ? "1" : "0") + "|"
                + this.getTask() + "|" + this.by;
    }
    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[D]" + "[" + isCompleted + "] " + super.getTask() + "(by:" + this.by + ")";
    }
}
