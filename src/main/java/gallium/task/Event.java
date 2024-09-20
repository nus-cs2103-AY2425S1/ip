package gallium.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import java.util.logging.Logger;

import gallium.main.GalliumException;

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

    private static final Logger logger = Logger.getLogger(Event.class.getName());


    private SimpleDateFormat inputTime = new SimpleDateFormat("HHmm");
    private SimpleDateFormat outputTime = new SimpleDateFormat("hh:mm a");

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
    public Event(String description) throws GalliumException, ParseException {
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

    private void parseDateTime() throws GalliumException, ParseException {
        String fromDateString = from.split(" ")[0];
        String toDateString = to.split(" ")[0];
        Date fromTime24 = parseTime(from);
        Date toTime24 = parseTime(to);
        if (checkFromBeforeTo(fromDateString, toDateString, fromTime24, toTime24)) {
        this.fromDate = LocalDate.parse(fromDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.toDate = LocalDate.parse(toDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.fromTime = outputTime.format(fromTime24);
        this.toTime = outputTime.format(toTime24);
        } else {
            throw new GalliumException("3:( From must be before to!! Please reenter your input!!");
        }
    }

    private Date parseTime(String message) throws ParseException {
        Date time24 = inputTime.parse(message.split(" ")[1]);
        return time24;
    }


    @Override
    public void setDesc(String message) {
        this.desc = message;
    }

    public void setFrom(String from) throws GalliumException, ParseException {
        String fromDateString = from.split(" ")[0];
        logger.info("fromdatestring:" + fromDateString);
        String toDateString = LocalDate.parse(this.toDate, DateTimeFormatter.ofPattern("MMM dd yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-d"));
        logger.info("\ntodatestring:" + toDateString);
        Date fromTime24 = parseTime(from);
        logger.info("\nfromtime24: "+inputTime.format(fromTime24));
        Date toTime12 = outputTime.parse(this.toTime);
        Date toTime24 = inputTime.parse(inputTime.format(toTime12));
        logger.info("\ntotime24: "+inputTime.format(toTime24));
        if (checkFromBeforeTo(fromDateString, toDateString, fromTime24, toTime24)) {
        this.fromDate = LocalDate.parse(fromDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.fromTime = outputTime.format(fromTime24);
        this.from = from;
        } else {
            throw new GalliumException("3:( From must be before to!! Please reenter your input!!");
        }
    }

    public void setTo(String to) throws GalliumException, ParseException {
        logger.info("setTo started");
        logger.info("\nfromdate:"+this.fromDate);
        String fromDateString = LocalDate.parse(this.fromDate, DateTimeFormatter.ofPattern("MMM d yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logger.info("fromdatestring:" + fromDateString);
        String toDateString = to.split(" ")[0];
        logger.info("\ntodatestring:" + toDateString);
        Date fromTime12 = outputTime.parse(this.toTime);
        Date fromTime24 = inputTime.parse(inputTime.format(fromTime12));
        logger.info("\nfromtime24: "+inputTime.format(fromTime24));
        Date toTime24 = parseTime(to);
        logger.info("\ntotime24: "+inputTime.format(toTime24));       
        if (checkFromBeforeTo(fromDateString, toDateString, fromTime24, toTime24)) {
        this.toDate = LocalDate.parse(toDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.toTime = outputTime.format(toTime24);
        this.to = to;
        } else {
            throw new GalliumException("3:( From must be before to!! Please reenter your input!!");
        }
    }

    private boolean checkFromBeforeTo(String fromDateString, String toDateString, Date fromTime, Date toTime) {
        LocalDate from = LocalDate.parse(fromDateString);
        LocalDate to = LocalDate.parse(toDateString);
        boolean fromBeforeTo = from.isBefore(to);
        boolean fromEqualsTo = from.equals(to);
        boolean fromBeforeToTime = fromTime.before(toTime);
        return fromBeforeTo
        ? fromBeforeTo
        : fromEqualsTo
        ? fromBeforeToTime
        : false;
    }
    
}
