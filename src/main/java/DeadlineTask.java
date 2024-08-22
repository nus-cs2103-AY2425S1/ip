public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toSaveString() {
        return String.format("D | %s | %s",
                super.toSaveString(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
