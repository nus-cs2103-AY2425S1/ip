public class Deadline extends Task{
    private String by;

    public Deadline(String taskName) {
        super(taskName.split("/by", 2)[0]);
        by = taskName.split("/by", 2)[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
