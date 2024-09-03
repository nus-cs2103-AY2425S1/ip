public class Deadline extends Task {
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    public String toSaveFormat() {
        return String.format("D_%s_%s_%s", isDone ? "1" : "0", getDesc(), deadline);
    }
}
