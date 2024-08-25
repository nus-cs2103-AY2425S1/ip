public class Deadlines extends Task{

    String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the Deadline as a string with its status, description and deadline
     *
     * @return a String of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
