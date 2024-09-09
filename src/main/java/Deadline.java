public class Deadline extends Task {

    private String deadline;

    public Deadline(String info, String deadline) {
        super(info);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return "[D]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo() + " (by: " + deadline + ")";
    }

    public String toFileFormat() {
        return "D | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo() + " | " + deadline;
    }
}
////