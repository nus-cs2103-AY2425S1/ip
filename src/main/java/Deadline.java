public class Deadline extends Task{
    protected String deadline;
    public Deadline(String desccription, String deadline) {
        super(desccription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
