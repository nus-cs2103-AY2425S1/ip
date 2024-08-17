public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X]" : "[ ]") + " " + description + " (by: " + this.deadline + ")";
    }
}
