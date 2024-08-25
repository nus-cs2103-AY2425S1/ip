public class Deadlines extends Task{

    String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the Deadline as a string with its status, description and deadline
     *
     * @return String of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Returns the Deadline as a fancier string with its status, description and deadline
     * Meant for recording in text files
     *
     * @return Fancier string of the Deadline
     */
    @Override
    public String toFancyString() {
        return "Deadline | " + super.getStatus() + " | " +
                super.getDescription() + " | "  + this.deadline;
    }
}
