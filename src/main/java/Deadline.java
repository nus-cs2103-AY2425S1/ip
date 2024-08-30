public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String formatTask() {
        String status = isDone? "1" : "0";
        return "D | " + this.description + " | " + status + " | " + this.description + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}