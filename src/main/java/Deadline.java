public class Deadline extends Task {

    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String str = "[D]" + super.toString() + String.format("( by: %s)", this.deadline);
        return str;
    }
}
