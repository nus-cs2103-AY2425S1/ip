package duke;

import java.time.LocalDate;

public class Event extends Task{
    LocalDate fromdate;
    LocalDate todate;
    public Event(String descr, String start, String end) {
        super(descr);
        fromdate = LocalDate.parse(start);
        todate = LocalDate.parse(end);
    }
    /**
     * Returns date of event task, when Storage.save() is called.
     *
     * @return String date.
     * */
    public String getDates() {
        return " | " + fromdate + " | "+todate;
    }

    /**
     * Returns description and date of event task, when TaskList.printTasks() is called.
     *
     * @return String task description and date.
     * */
    public String toString() {
        return "[E]" + super.toString() + "(from: " + super.dateConverter(fromdate) + " to: " + super.dateConverter(todate) + ")";
    }
}