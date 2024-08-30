package task;

import ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate dueTime;
    public Deadline() {
        super("", "D");
    }

    public void convertStringToTask(String[] slicedStr) {
        String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
        String deadlineTaskDetail = String.join(" ", task);
        String[] taskParts = deadlineTaskDetail.split(" /by ");
        this.description = taskParts[0];
        this.dueTime = parseDate(taskParts[1]);
    }

    public void convertSavedDataToTask(String[] dataArr) {
        this.setMarkStatus(dataArr[1].equals("1"));
        this.description = dataArr[2];
        this.dueTime = parseDate(dataArr[3]);
    }

    public LocalDate parseDate(String dateStr) {
        List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM/dd/yy"),
            DateTimeFormatter.ofPattern("MMM d yyyy"),
            DateTimeFormatter.ofPattern("MMM dd yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d-MMMM-yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("d MMM"),
            DateTimeFormatter.ofPattern("d MMMM")
        );

        for (DateTimeFormatter formatter : formatters) {
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
        throw new IllegalArgumentException("Oops! Date format not recognized: " + dateStr + " If you have entered a time, please remove it.");
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
