package duke;

import java.time.LocalDate;
/**
 * Represents a Task with a specified start and end time.
 */
public class Event extends Task{
    LocalDate fromDate;
    LocalDate toDate;
    public Event(String descr, String start, String end) {
        super(descr);
        fromDate = LocalDate.parse(start);
        toDate = LocalDate.parse(end);
    }
    /**
     * Returns date of event task, when Storage.save() is called.
     *
     * @return String date.
     * */
    public String getDates() {
        return " | " + fromDate + " | "+toDate;
    }

    /**
     * Returns description and date of event task, when TaskList.printTasks() is called.
     *
     * @return String task description and date.
     * */
    public String toString() {
        return "[E]" + super.toString() + "(from: " + super.dateConverter(fromDate) + " to: " + super.dateConverter(toDate) + ")";
    }
}