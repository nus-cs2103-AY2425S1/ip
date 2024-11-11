package bimo.tasks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Task with Start date and deadline.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Instantiates an Event object.
     *
     * @param details Description of task.
     * @param startDate Start date of task as LocalDate object.
     * @param endDate End date of task as LocalDate object.
     */
    public Event(String details, LocalDate startDate, LocalDate endDate) {
        super(details);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Formats start and end date as texts to write into file.
     *
     * @return Start and end date as text separated by /.
     */
    public String getDatesAsText() {
        String dateAsText = "|" + this.startDate.toString() + "/"
                + this.endDate.toString();
        return dateAsText;
    }

    /**
     * Converts task to string value with priority, task type, description,
     * start and due date.
     *
     * @return String value of task.
     */
    @Override
    public String toString() {
        String startDateFormat = this.startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String endDateFormat = this.endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String eventString = convertPriorityToString() + String.format("[E]%s (from: %s to: %s)",
                super.toString(), startDateFormat, endDateFormat);
        return eventString;
    }
}
