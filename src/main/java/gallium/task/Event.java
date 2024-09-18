package gallium.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected String desc;
    protected String from;
    protected String to;
    protected String fromDate;
    protected String fromTime;
    protected String toDate;
    protected String toTime;

    /**
     * Constructs an Event task with a description.
     * 
     * @param description The description of the Event task, which has its start and
     *                    end dates and times.
     * @throws ParseException         If there is an error parsing the time.
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public Event(String description) throws ParseException {
        super(description);
        try {
            if (description.startsWith("event ")) {
                this.desc = description.split("event ")[1].split(" /from")[0];
                this.from = description.split("/from ")[1].split(" /to")[0];
                this.to = description.split("/to ")[1];
                String fromDateString = from.split(" ")[0];
                this.fromDate = LocalDate.parse(fromDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                SimpleDateFormat inputTime = new SimpleDateFormat("HHmm");
                SimpleDateFormat outputTime = new SimpleDateFormat("hh:mm a");
                Date time24 = inputTime.parse(from.split(" ")[1]);
                this.fromTime = outputTime.format(time24);
                String toDateString = to.split(" ")[0];
                this.toDate = LocalDate.parse(toDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                time24 = inputTime.parse(to.split(" ")[1]);
                this.toTime = outputTime.format(time24);
            } else {
                parse(description);
            }
        } catch (ParseException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw e;
        }

    }

    /**
     * Returns a string representation of the Event task.
     * 
     * @return A string representation of the Event task in the format "[E][X/
     *         ][description] (from: startDate, startTime to: endDate, endTime)".
     */
    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + this.desc + " (from: " +
                this.fromDate + ", " + this.fromTime + " to: " +
                this.toDate + ", " + this.toTime + ")";
    }

    /**
     * Returns the from date of the Event task.
     * 
     * @return The from date of the Event task.
     */
    public String getFromDate() {
        return this.fromDate;
    }

    /**
     * Returns the to date of the Event task.
     * 
     * @return The to date of the Event task.
     */
    public String getToDate() {
        return this.toDate;
    }

    private void parse(String description) {
        String[] parts = {};
        if (description.startsWith("[E][ ] ")) {
            parts = description.split("\\[E\\]\\[ \\] ");
            this.isDone = false;
        } else if (description.startsWith("[E][X] ")) {
            parts = description.split("\\[E\\]\\[X\\] ");
            this.isDone = true;
        }
            this.desc = parts[1].split(" \\(from:")[0];
            String[] fromParts = parts[1].split("\\(from: ");
            this.from = fromParts[1].split(" to:")[0];
            String[] toParts = fromParts[1].split(" to: ");
            this.to = toParts[1].split("\\)")[0];
            this.fromDate = from.split(", ")[0];
            this.fromTime = from.split(", ")[1];
            this.toDate = to.split(", ")[0];
            this.toTime = to.split(", ")[1];
    }
    
}
