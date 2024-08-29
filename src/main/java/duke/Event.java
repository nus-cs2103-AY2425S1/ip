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

    public String getDates() {
        return " | " + fromdate + " | "+todate;
    }
    public String toString() {
        return "[E]" + super.toString() + "(from: " + super.dateConverter(fromdate) + " to: " + super.dateConverter(todate) + ")";
    }
}