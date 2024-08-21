public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String toString() {
        String str = " (by: " + deadline + ")";
        return "[D]" + super.toString() + str;
    }
}
