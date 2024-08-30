import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String deadline;
    LocalDate date;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
        String[] strings = this.deadline.split(" "); // in the format of by, date, time
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

    public String getDeadline() {
        return this.deadline;
    }
    public LocalDate getDate() {
        return this.date;
    }
}
