package mahesh.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import mahesh.util.MaheshException;

/**
 * Represents a Deadline task which is a specific type of Task.
 * A Deadline task has a due date/time.
 */
public class Deadline extends Task {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static DateTimeFormatter dateStringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss");

    /**
     * The due date/time of the deadline.
     */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and due date/time.
     *
     * @param description The description of the Deadline task.
     * @param by The due date/time of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the specified description, due date/time, and completion status.
     *
     * @param description The description of the Deadline task.
     * @param by The due date/time of the Deadline task.
     * @param isDone The completion status of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Deadline task, including its type indicator
     * and due date/time.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(dateStringFormatter) + ")";
    }

    /**
     * Converts the Deadline task to a string format suitable for saving to a file.
     * The format includes the type indicator "D", the completion status, the description of the task,
     * and the due date/time.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileEntry() {
        return "D/" + super.toFileEntry() + "/" + by;
    }

    /**
     * Parses a StringTokenizer to create a new Deadline task.
     *
     * @param tokenizedInput The StringTokenizer containing the description and due date/time of the Deadline task.
     * @return A new Deadline task with the parsed description and due date/time.
     * @throws MaheshException If the input does not contain the expected tokens.
     */
    public static Deadline parseDeadline(StringTokenizer tokenizedInput) throws MaheshException {
        try {
            StringBuilder description = new StringBuilder();
            String token = tokenizedInput.nextToken();
            while (!token.equals("/by")) {
                description.append(token).append(" ");
                token = tokenizedInput.nextToken();
            }
            StringBuilder by = new StringBuilder();
            token = tokenizedInput.nextToken();
            by.append(token).append(" ");
            while (tokenizedInput.hasMoreTokens()) {
                token = tokenizedInput.nextToken();
                by.append(token).append(" ");
            }
            return new Deadline(description.toString().trim(),
                LocalDateTime.parse(by.toString().trim(), dateFormatter));
        } catch (Exception err) {
            throw new MaheshException(
                "Please follow the given format: deadline <deadline_desc> /by yyyy-mm-ddTHH:mm:ss"
            );
        }
    }
}