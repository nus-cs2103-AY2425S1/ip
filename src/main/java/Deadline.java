public class Deadline extends DatedTask{
    public Deadline(String task, String date) {
        super(task, date);
    }

    public Deadline(String task, String date, String time) {
        super(task, date, time);
    }
    public Deadline() {
        super();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate().toString() + " " + this.getTime() + ")";
    }
}
