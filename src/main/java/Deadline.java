public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return this.getTaskType() + super.toString() + "(by: " + this.date + ")";
    }
}
