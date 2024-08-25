public class Deadline extends Task {
    private String type = "D";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getTaskInfo() {
        return(String.format("%s (by: %s)", super.description, this.deadline));
    }

    public String getTaskType() {
        return(this.type);
    }
}
