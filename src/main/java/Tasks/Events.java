package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start date and end date.
 */
public class Events extends Task {

    private LocalDate start;
    private LocalDate end;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Takes in a string for description, string for start date, and string for end date.
     * Converts string for start and end date to LocalDate.
     *
     * @param s Description of task.
     * @param start Start date.
     * @param end End date.
     */
    public Events(String s, String start, String end) {
        super(s);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String infoForFile() {
        String str = "[E] / " + super.getDetails();
        str += " / " + start + " / " + end;
        return str;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String str = "";
        str = str + "[E]";
        str = str + super.toString();
        str = str + String.format("(from: %s to: %s)",
                formatter.format(start), formatter.format(end));
        return str;
    }
}
