public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, String deadline, int type) {
        super(description, type);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + deadline + ")";
    }
}
