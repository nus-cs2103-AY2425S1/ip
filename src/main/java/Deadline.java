public class Deadline extends Task{
    private String deadline;
    public Deadline(String desccription, String deadline, boolean isMarked) {
        super(desccription, isMarked);
        this.deadline = deadline;

    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
