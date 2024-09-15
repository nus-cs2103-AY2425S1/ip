package Johnson.task;

/**
 * Represents a task with a deadline, which includes a date and optional time.
 */
public class Deadline extends DatedTask{
    public Deadline(String task, String date, String... tags) {
        super(task, date, tags);
    }

    public Deadline(String task, String date, String time, String... tags) {
        super(task, date, time, tags);
    }
    public Deadline() {
        super();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate().toString() + " " + (this.getTime() == null ? "" : this.getTime()) + ")";
    }
}
