package luke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The {@code Deadline} class represents the Deadline task type. The Deadline task contains a task name,
 *
 * <p>
 * The task description is shown in the format:
 * <pre>
 * [D][X] Task Name (by: Deadline Date)
 * </pre>
 * where the "D" represents a "Deadline" task, and the "X" (or blank) represents the completion status.
 * </p>
 * The save format for a Deadline task is as follows:
 * <pre>
 * X | deadline | Task Name by Deadline Date
 * </pre>
 * where "X" represents that the task is marked as done, and "-" indicates it is not done.
 * </p>
 *
 * @see Task
 * @see Todo
 * @see Event
 */
public class Deadline extends Task {
    protected static final List<DateTimeFormatter> DATE_TIME_INPUT_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"), // e.g., "3/9/2024, 1430" for 3 Sept 2024, 1430 hrs
            DateTimeFormatter.ofPattern("dd/M/yyyy"), // e.g., "23/9/2024, 14:30" for 23 Sept 2024, 14:30 hrs
            DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"), // e.g., "Sep 23 2024, 14:30"
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"), // e.g., "23/09/2024 14:30"
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), // e.g., "2024-09-23 14:30"
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"), // e.g., "09-23-2024 14:30"
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"), // e.g., "23 Sep 2024, 14:30"
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"), // e.g., "2024/09/23 14:30"
            DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"), // e.g., "Sep 23, 2024 14:30"
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") // e.g., "23-09-2024 14:30"
    );
    protected static final List<DateTimeFormatter> DATE_INPUT_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("d/M/yyyy"), // e.g., "3/9/2024" for 3 Sept 2024
            DateTimeFormatter.ofPattern("dd/M/yyyy"), // e.g., "23/9/2024" for 23 Sept 2024
            DateTimeFormatter.ofPattern("dd/MM/yyyy"), // e.g., "23/09/2024"
            DateTimeFormatter.ofPattern("MMM dd yyyy"), // e.g., "Sep 23, 2024"
            DateTimeFormatter.ofPattern("dd/MM/yyyy"), // e.g., "23/09/2024"
            DateTimeFormatter.ofPattern("yyyy-MM-dd"), // e.g., "2024-09-23"
            DateTimeFormatter.ofPattern("MM-dd-yyyy"), // e.g., "09-23-2024"
            DateTimeFormatter.ofPattern("dd MMM yyyy"), // e.g., "23 Sep 2024"
            DateTimeFormatter.ofPattern("yyyy/MM/dd"), // e.g., "2024/09/23"
            DateTimeFormatter.ofPattern("MMM dd, yyyy"), // e.g., "Sep 23, 2024"
            DateTimeFormatter.ofPattern("dd-MM-yyyy") // e.g., "23-09-2024"
    );

    protected static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    protected static final DateTimeFormatter DATE_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    protected String deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified task name, deadline, and completion status.
     *
     * @param taskName The name of the deadline task.
     * @param deadline The due date and time for the task.
     * @param isDone   The completion status of the deadline task. {@code true} if the task is completed.
     */
    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = parseDateTime(deadline);
    }

    /**
     * Parses the given deadline string into a formatted date or date-time string.
     * <p>
     * This method tries to parse the input string using a series of predefined {@link DateTimeFormatter}
     * patterns. It first attempts to parse the string as a {@link LocalDateTime} (i.e., containing both
     * date and time). If none of the date-time patterns match, it then attempts to parse the string as
     * a {@link LocalDate} (i.e., date only).
     * </p>
     * <p>
     * If the input string is successfully parsed as a {@code LocalDateTime}, it will be formatted
     * using {@code "MMM d yyyy, HH:mm"} (e.g., "Sep 23 2024, 14:30"). If the input string is parsed
     * as a {@code LocalDate}, it will be formatted using {@code "MMM d yyyy"} (e.g., "Sep 23 2024").
     * </p>
     *
     * @param deadline The input string representing the deadline in various date or date-time formats.
     * @return The formatted deadline string if parsing is successful. Otherwise, it will return the unformatted string.
     */
    public String parseDateTime(String deadline) {
        // 1. Try to parse as a LocalDateTime (date + time)
        for (DateTimeFormatter formatter : DATE_TIME_INPUT_FORMATTERS) {
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(deadline, formatter);
                return parsedDate.format(DATE_TIME_OUTPUT_FORMATTER);
            } catch (DateTimeParseException ignored) {
                // Ignore and try the next formatter
            }
        }

        // 2. If no LocalDateTime format worked, try parsing as a LocalDate (date-only)
        for (DateTimeFormatter formatter : DATE_INPUT_FORMATTERS) {
            try {
                LocalDate parsedDate = LocalDate.parse(deadline, formatter);
                return parsedDate.format(DATE_OUTPUT_FORMATTER);
            } catch (DateTimeParseException ignored) {
                // Ignore and try the next formatter
            }
        }

        // If the deadline string doesn't match any format, return the unformatted deadline.
        return deadline;
    }

    @Override
    public String taskDescription() {
        return "[D]" + showMark() + " " + this.name
                + " (by: " + deadline + ")";
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | deadline | "
                + name + " by " + deadline + "\n";
    }
}
