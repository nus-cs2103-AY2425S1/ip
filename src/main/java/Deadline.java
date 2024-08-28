public class Deadline extends Task {

    private String deadline;
    
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        return "D," + done + "," + this.getName() + "," + this.deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline + ")";
    }

}
