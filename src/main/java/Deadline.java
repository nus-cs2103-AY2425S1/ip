public class Deadline extends Task{
    String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String printTaskOnList() {
        if (marked) {
            return "[D][X] " + this.task + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.task + " (by: " + this.deadline + ")";
        }
    }
}
