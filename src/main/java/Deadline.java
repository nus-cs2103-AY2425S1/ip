public class Deadline extends Task{
    String deadline;

    public Deadline(String task, String deadline, boolean isMarked) {
        super(task, isMarked);
        this.deadline = deadline;
    }

    @Override
    public String printTaskOnList() {
        if (isMarked) {
            return "[D][X] " + this.task + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.task + " (by: " + this.deadline + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isMarked ? "1" : "0") + " | " + this.task + " | " + this.deadline;
    }
}
