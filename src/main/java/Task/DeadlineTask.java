package Task;

public class DeadlineTask extends Task{
    private String by;

    public DeadlineTask(String eventName, String by) {
        super(eventName);
        this.by = by;
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[D]" + "[" + isCompleted + "] " + super.getTask() + "(by:" + this.by + ")";
    }
}
