package Johnson.task;

/**
 * Represents a task with a deadline, which includes a date and optional time.
 */
public class Deadline extends DatedTask{
    /**
     * Constructs a Deadline with the specified task name, date, and optional tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param tags the tags of the task.
     */
    public Deadline(String task, String date, String... tags) {
        super(task, date, tags);
    }

    /**
     * Constructs a Deadline with the specified task name, date, time, and optional tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param time the time of the task.
     * @param tags the tags of the task.
     */
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
