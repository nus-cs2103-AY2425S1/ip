public class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String getDescription() {
        return this.getTaskType() + super.getDescription().replace("\n", "") + String.format("(by:%s)\n", this.deadline) ;
    }
}
