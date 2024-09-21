package notgpt.tasks;
import notgpt.parsers.DateParser;

/**
 * Represents an event task that occurs within a specific time frame.
 * <p>
 * An event has a description, a start time, and an end time. The start and end times
 * are specified using the "/from" and "/to" keywords, respectively, within the input string.
 * </p>
 */
public class Event extends Task {
    private String from;
    private String to;

    private DateParser dateParser = new DateParser();

    /**
     * Constructs an {@code Event} object from a string input.
     * <p>
     * The input string is expected to contain a task description followed by
     * a "from" time and a "to" time, separated by the "/" character.
     * </p>
     *
     * @param s the string containing the task description and time information
     * @throws IllegalArgumentException if the input string is invalid, missing the task description,
     *                                  or missing the "/from" or "/to" {@link String}.
     */
    public Event(String s) {
        super(s.split(" /")[0].trim());
        String[] parts = s.split(" /");
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.startsWith("from ")) {
                this.from = part.substring(5);
            } else if (part.startsWith("to ")) {
                this.to = part.substring(3);
            }
        }
        if (parts[0].trim().isEmpty() || from == null || to == null) {
            throw new IllegalArgumentException("Events must include both task description and '/from' "
                    + "and '/to' times and not include extra \"/\"...");
        }
        String toDate = dateParser.giveDate(to);
        if (toDate != null) {
            this.to = toDate;
        }
        String fromDate = dateParser.giveDate(from);
        if (fromDate != null) {
            this.from = fromDate;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
