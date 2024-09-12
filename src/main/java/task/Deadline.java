package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The Deadline class represents a task with a deadline date.
 * It extends the Task class.
 */
public class Deadline extends Task {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM/dd/yy"),
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("MMM dd yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d-MMMM-yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("d MMM"),
            DateTimeFormatter.ofPattern("d MMMM")
    );
    protected LocalDate dueTime;

    /**
     * Constructs a Deadline object with an empty description and a type of "D".
     */
    public Deadline() {
        super("", "D");
    }

    /**
     * Converts user input into a Deadline task.
     *
     * @param slicedStrings The array of strings representing the user input.
     */
    public void convertStringToTask(String[] slicedStrings) {
        String[] task = Arrays.copyOfRange(slicedStrings, 1, slicedStrings.length);
        String deadlineTaskDetail = String.join(" ", task);
        String[] taskParts = deadlineTaskDetail.split(" /by ");
        this.description = taskParts[0];
        this.dueTime = parseDate(taskParts[1]);
    }

    /**
     * Converts saved data into a Deadline task.
     *
     * @param dataArr The array of strings representing the saved data.
     */
    public void convertSavedDataToTask(String[] dataArr) {
        this.setMarkStatus(dataArr[1].equals("1"));
        this.description = dataArr[2];
        this.dueTime = parseDate(dataArr[3]);
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string to be parsed.
     * @return The parsed LocalDate object.
     * @throws IllegalArgumentException if the date string cannot be parsed.
     */
    public LocalDate parseDate(String dateStr) {
        assert dateStr != null : "date string should not be empty";

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(dateStr, formatter);
                // If no year was provided, assume the current year
                if (formatter.toString().contains("MMM") && !dateStr.matches(".*\\d{4}.*")) {
                    return date.withYear(LocalDate.now().getYear());
                }
                return date;
            } catch (DateTimeParseException e) {
                // continues to the next format if parsing fails
            }
        }
        throw new IllegalArgumentException("Oops! Date format not recognized: "
                                            + dateStr + " If you have entered a time, please remove it.");
    }

    @Override
    public String toSavedFormat(String separation) {
        return super.toSavedFormat(separation) + separation + dueTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return this.description + " (by: " + this.dueTime + ")";
    }
}
