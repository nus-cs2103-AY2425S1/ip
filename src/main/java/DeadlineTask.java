public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private String getTypeLabel() {
        return "D";
    }

    private String getDeadlineLabel() {
        return String.format("(by: %s)", this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getTypeLabel(), super.toString(), this.getDeadlineLabel());
    }
}
