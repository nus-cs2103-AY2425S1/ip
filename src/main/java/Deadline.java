public class Deadline extends Task{
    private String deadline;

    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by")));
        this.deadline = description.substring(description.indexOf("/by") + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
