package dawn;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String time;
    LocalDate date;

    /**
     * Creates a new instance of a time task with the task description and time
     *
     * @param desc
     * @param deadline
     */
    public Deadline(String desc, String deadline) throws DawnException {
        super(desc);
        String[] strings = deadline.split(" "); // in the format of by, date, time
        if (strings.length < 3) {
            throw new DawnException("Make sure you include both the task description and the time in this " +
                    "format:\n time [task name] /by [date yyyy-mm-dd] [time]\n" +
                    "For example: time submit assignment1 /by 2024-09-16 2pm");
        }
        this.time = strings[2];
        this.date = LocalDate.parse(strings[1]);
    }

    @Override
    public String toString() {
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("  [D][%s] %s (by: %s %s)", super.getStatusIcon(), super.toString(), d, this.time);
    }

    /**
     * Returns the time of the task as a String
     */
    public String getDeadlineTime() {
        return this.time;
    }

    /**
     * Returns the time of the task in LocalDate format
     */
    public LocalDate getDate() {
        return this.date;
    }
}
