public class Deadline extends Task{
    private String description;
    private String deadline;
    private boolean isMarked;
    public Deadline(String desccription, String deadline) {
        this.description = desccription;
        this.deadline = deadline;
        this.isMarked = false;
    }

    @Override
    public void mark() {
        this.isMarked = true;
    }

    @Override
    public void unmark() {
        this.isMarked = false;
    }
    @Override
    public String toString() {
        return "[D] " + this.description + " (by: " + this.deadline + ")";
    }
}
