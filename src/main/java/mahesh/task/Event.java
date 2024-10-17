package mahesh.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import mahesh.util.MaheshException;

/**
 * Represents an Event task which is a specific type of Task.
 * An Event task has a start time and an end time.
 */
public class Event extends Task {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static DateTimeFormatter dateStringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss");

    /**
     * The start time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     * @param isDone The completion status of the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this(description, from, to);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Event task, including its type indicator,
     * start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(dateStringFormatter)
            + " to: " + to.format(dateStringFormatter) + ")";
    }

    /**
     * Converts the Event task to a string format suitable for saving to a file.
     * The format includes the type indicator "E", the completion status, the description of the task,
     * the start time, and the end time.
     *
     * @return A string representation of the Event task for file storage.
     */
    @Override
    public String toFileEntry() {
        return "E/" + super.toFileEntry() + "/" + from + "/" + to;
    }

    /**
     * Parses a StringTokenizer to create a new Event task.
     *
     * @param tokenizedInput The StringTokenizer containing the description, start time, and end time of the Event task.
     * @return A new Event task with the parsed description, start time, and end time.
     * @throws MaheshException If the input does not contain the expected tokens.
     */
    public static Event parseEvent(StringTokenizer tokenizedInput) throws MaheshException {
        try {
            StringBuilder description = new StringBuilder();
            String token = tokenizedInput.nextToken();
            while (!token.equals("/from")) {
                description.append(token).append(" ");
                token = tokenizedInput.nextToken();
            }
            StringBuilder from = new StringBuilder();
            token = tokenizedInput.nextToken();
            while (!token.equals("/to")) {
                from.append(token).append(" ");
                token = tokenizedInput.nextToken();
            }
            StringBuilder to = new StringBuilder();
            token = tokenizedInput.nextToken();
            to.append(token).append(" ");
            while (tokenizedInput.hasMoreTokens()) {
                token = tokenizedInput.nextToken();
                to.append(token).append(" ");
            }
            return new Event(description.toString().trim(),
                LocalDateTime.parse(from.toString().trim(), dateFormatter),
                LocalDateTime.parse(to.toString().trim(), dateFormatter));
        } catch (Exception err) {
            throw new MaheshException(
                "Please follow the given format: event <event_desc> /from yyyy-mm-ddTHH:mm:ss /to yyyy-mm-ddTHH:mm:ss"
            );
        }
    }
}
