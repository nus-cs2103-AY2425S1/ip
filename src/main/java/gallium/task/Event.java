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


    private static final String EVENT = "event ";
    private static final String FROM_SPACE = "/from ";
    private static final String TO_SPACE = "/to ";
    private static final String SPACE_FROM = " /from";
    private static final String SPACE_TO = " /to";
    private static final String FROM_COLON = "from:";
    private static final String TO_COLON = "to:";

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
            if (description.startsWith(EVENT)) {
                this.desc = description.split(EVENT)[1].split(SPACE_FROM)[0];
                this.from = description.split(FROM_SPACE)[1].split(SPACE_TO)[0];
                this.to = description.split(TO_SPACE)[1];
                parseDateTime();               
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
            this.desc = parts[1].split(" \\(" + FROM_COLON)[0];
            String[] fromParts = parts[1].split("\\(" + FROM_COLON + " ");
            this.from = fromParts[1].split(" " + TO_COLON)[0];
            String[] toParts = fromParts[1].split(" " + TO_COLON + " ");
            this.to = toParts[1].split("\\)")[0];
            this.fromDate = from.split(", ")[0];
            this.fromTime = from.split(", ")[1];
            this.toDate = to.split(", ")[0];
            this.toTime = to.split(", ")[1];
    }

    private void parseDateTime() throws ParseException {
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
    }
    
}
