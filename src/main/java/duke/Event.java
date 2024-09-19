package duke;

import java.time.LocalDate;

/**
 * Represents a Task with a specified start and end time.
 */
public class Event extends Task{
    private LocalDate fromDate;
    private LocalDate toDate;
    private static final String type = "[E]";

    public Event(String description, String startDate, String endDate) {
        super(description);
        fromDate = LocalDate.parse(startDate);
        toDate = LocalDate.parse(endDate);
    }

    /**
     * Returns date of event task, when Storage.save() is called.
     *
     * @return String format of dates.
     * */
    public String getDates() {
        return " | " + fromDate + " | " + toDate;
    }

    /**
     * Returns description and date of event task, when TaskList.printTasks() is called.
     *
     * @return String format of task description and date.
     * */
    public String toString() {
        String period = "(from: " + convertDates(fromDate) + " to: " + convertDates(toDate) + ")";
        return type + super.toString() + period;
    }
}