public class Deadline extends Task {
    private String date;
    public Deadline(String name, boolean done, String date) {
        super(name, done);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
