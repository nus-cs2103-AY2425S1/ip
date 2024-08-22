public class Deadline extends Task{
    private final String deadline;
    public Deadline(String taskName) {
        super(taskName.split("/by")[0]);
        this.deadline = taskName.split("/by")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }

}
