public class Deadline extends DatedTask{
    public Deadline(String task, String deadline) {
        super(task, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDate() + ")";
    }
}
