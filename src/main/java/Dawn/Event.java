package Dawn;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String start;
    String end;
    LocalDate date;

    /**
     * Creates a new instance of an event with task description, start time, and end time of the event
     *
     * @param desc
     * @param start
     * @param end
     */
    public Event(String desc, String start, String end) throws DawnException {
        super(desc);
        int indexOfFrom = start.indexOf(" ") + 1;
        int indexOfEnd = end.indexOf(" ") + 1;
        String [] s = start.substring(indexOfFrom).split(" ");
        if (s.length < 2) {
            throw new DawnException("Make sure you include the task description, start, and end times for " +
                    "your event in this format:\nevent [task name] /from [date yyyy-mm-dd] [time] / " +
                    "to [time]\n For example: event party /from 2024-09-01 5pm /to 9pm");
        }
        this.date = LocalDate.parse(s[0]);
        this.start = s[1];
        this.end = end.substring(indexOfEnd).trim();
    }

    @Override
    public String toString() {
        String d = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("  [E][%s] %s (from: %s %s to: %s)",
                super.getStatusIcon(), super.toString(), d, this.start, this.end);
    }

    /**
     * Returns the start time of the event as a String
     */
    public String getFromTime() {
        return this.start;
    }

    /**
     * Returns the end time of the event as a String
     */
    public String getToTime() {
        return this.end;
    }

    /**
     * Returns the date of the event in LocalDate format
     */
    public LocalDate getDate() {
        return this.date;
    }
}
