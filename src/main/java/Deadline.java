public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskInfo, String deadline) {
        super(taskInfo);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String s = "[D] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (by: " + this.deadline + ")";
        return s;
    }

    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "D | " + i + " | " + this.getTaskInfo() + " | " + deadline;
    }
}
