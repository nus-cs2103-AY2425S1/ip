public class Deadline extends Task {
    protected String by;
    public Deadline(String taskDescription, String by) throws JustbotException {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.by + ")";
    }
}
