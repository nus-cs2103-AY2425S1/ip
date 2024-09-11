public class Deadline extends Task{
    protected String deadline;
    /**
     * Private constructor for a Task
     *
     * @param description A string description of the task
     * @param deadline A string description of the deadline of the task
     */
    protected Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Alternative constructor for an event, specifying the current status of the task.
     * @param description A string description of the task
     * @param status The current status of the task
     * @param deadline The deadline of this task
     */
    protected Deadline(String description, String status, String deadline) {
        super(description, Status.valueOf(status));
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[D]");
        str.append(super.toString());
        str.append("(Deadline: ");
        str.append(deadline);
        str.append(")");
        return str.toString();
    }

    /**
     * Returns a csv representation of this deadline.
     * @return A string in the form "Deadline,(task description),(task status),(deadline)"
     */
    @Override
    protected String toCsv() {
        StringBuilder csv = new StringBuilder();
        csv.append("Deadline,");
        csv.append(super.toCsv());
        csv.append(",");
        csv.append(this.deadline);
        return csv.toString();
    }
}
