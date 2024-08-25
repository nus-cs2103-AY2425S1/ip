public class Deadline extends Task{
    private String deadline;
    public Deadline(String task, String by) {
        super(task);
        this.deadline = by;
    }

    public void editDeadline(String deadline) {
        this.deadline = deadline;
    }
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.description + String.format(" (by: %s)", this.deadline);
    }
}
