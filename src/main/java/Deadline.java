public class Deadline extends Task {

    protected String date;

    public Deadline(String name, boolean done, String date) {
        super(name, done);
        this.date = date;
    }

    public void changeDate(String newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
