public class Deadline extends Task{
    private final String deadline;
    public Deadline(String taskName) {
        super(taskName.split("/by")[0]);
        this.deadline = taskName.split("/by")[1];
    }

    public Deadline(String taskName, boolean isCompleted) {
        super(taskName.split("/by")[0], isCompleted);
        this.deadline = taskName.split("/by")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }

    public String writeToFile() {
        return "D " + (super.isCompleted() ? "0" : "1") + " " + this.getTaskName() + "/by" + this.deadline;
    }

}
