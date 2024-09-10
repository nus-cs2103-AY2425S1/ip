package Dawn;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String deadline;
    LocalDate date;

    /**
     * Creates a new instance of a deadline task with the task description and deadline
     *
     * @param desc
     * @param deadline
     */
    public Deadline(String desc, String deadline) throws DawnException {
        super(desc);
        String[] strings = deadline.split(" "); // in the format of by, date, time
        if (strings.length < 3) {
            throw new DawnException("Make sure you include both the task description and the deadline in this " +
                    "format:\n deadline [task name] /by [date yyyy-mm-dd] [time]\n" +
                    "For example: deadline submit assignment1 /by 2024-09-16 2pm");
        }
        this.deadline = strings[2];
        this.date = LocalDate.parse(strings[1]);
    }

    @Override
    public String toString() {
        String[] strings = this.deadline.split(" ");
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (strings.length > 2) {
            for (int i = 2; i < strings.length; i++) {
                d += " " + strings[i];
            }
        }
        return String.format("  [D][%s] %s (by: %s)", super.getStatusIcon(), super.toString(), d);
    }

    /**
     * Returns the deadline of the task as a String
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the deadline of the task in LocalDate format
     */
    public LocalDate getDate() {
        return this.date;
    }
}
