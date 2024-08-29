public class Deadline extends Task{
    private final String deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }
    @Override
    public String exportString() {
        String completedString;
        if (this.isCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("D|%s|%s|%s", completedString, this.getTaskName(), this.deadline);
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                                super.toString(),
                                this.deadline);
    }
}
