public class Deadline extends Task{
    protected String deadline;
    /**
     * Private constructor for a Task
     *
     * @param description A string description of the task
     * @param deadline A string description of the deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
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
}
