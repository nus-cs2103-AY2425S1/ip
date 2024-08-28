public class Deadline extends Task{
    private String dueDate;

    public Deadline(String taskDescription, String dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[D] " + task + "(by: " + dueDate + ")";
    }
    @Override
    public String toFileString() {
        return "D | " + this.getFileStatus() + " | " + this.getTask() + " | " + dueDate + "\n";
    }
}
