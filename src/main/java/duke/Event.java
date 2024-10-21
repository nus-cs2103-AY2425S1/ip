package duke;
import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    Event(String name, String[] eventTimings) {
        super(name);

        // if user gives date in yyyy-mm-dd, then we format it to MMM dd yyyy
        // otherwise we do nothing, as we accept other input types or formats
        // for date.

        try {
            LocalDate date = LocalDate.parse(eventTimings[0], DateTimeFormatter.ISO_DATE);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

            eventTimings[0] = date.format(myFormatObj);
        } catch (Exception e) {
            // do nothing
            System.out.println(e);
        }

        try {
            LocalDate date = LocalDate.parse(eventTimings[1], DateTimeFormatter.ISO_DATE);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd yyyy");

            eventTimings[1] = date.format(myFormatObj);
        } catch (Exception e) {
            // do nothing
        }

        this.eventTimings = eventTimings;
    }

    @Override
    public void mark() {
        this.isMarked = true;
    }

    @Override
    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String print() {
        String message = "";

        message += "[E]";

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += this.name;

        message += " (from: " + this.eventTimings[0] + " to: " + this.eventTimings[1] + ")";

        System.out.println(message);
        return message;
    }

    @Override
    public String print(int rank) {
        String message = rank + ".";

        message += "[E]";

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += name;

        message += " (from: " + this.eventTimings[0] + " to: " + this.eventTimings[1] + ")";

        System.out.println(message);
        return message;
    }

    public String[] eventTimings;
}
