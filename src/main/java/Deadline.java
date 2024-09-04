public class Deadline extends Task {

    private String deadline;

    public Deadline(String info, String deadline) {
        super(info);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return "[D]" + "[" + getStatus() + "]" + " " + getInfo() + " (by: " + deadline + ")";
    }

}
