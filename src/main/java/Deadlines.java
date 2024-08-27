public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String task, String deadline) {
        super(task);
        if (deadline.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!![@.@] Deadline cannot be empty" +
                    "\n\t Enter the deadline in the format: deadline <Task> /by <Deadline>");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "(by:" + this.deadline + ")";
    }
}
