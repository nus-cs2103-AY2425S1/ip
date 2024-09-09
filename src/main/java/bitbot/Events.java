package bitbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


/**
 * This is the Events class which extends Task
 * as this is where new Events objects are created
 */
public class Events extends Task {
    protected LocalDateTime beginningDateTime;
    protected LocalDateTime endingDateTime;
    protected LocalDate beginningDate;
    protected LocalDate endingDate;
    protected LocalTime beginningTime;
    protected LocalTime endingTime;
    protected String from;
    protected String to;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * This is an Events constructor that takes in a description, a "from" time and a "to" time
     *
     * @param description the description of the event
     * @param from the beginning time in string
     * @param to the ending time in string
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * This is an Events constructor that takes in a description, a "from" time (in dd/MM/yyyy HH:mm)
     * and a "to" time (in dd/MM/yyyy HH:mm)
     *
     * @param description the description of the event
     * @param beginningDateTime the beginning time in (in dd/MM/yyyy HH:mm) format
     * @param endingDateTime the ending time in (in dd/MM/yyyy HH:mm) format
     */
    public Events(String description, LocalDateTime beginningDateTime, LocalDateTime endingDateTime) {
        super(description);
        this.beginningDateTime = beginningDateTime;
        this.endingDateTime = endingDateTime;
    }

    /**
     * This is an Events constructor that takes in a description, a "from" time (in dd/MM/yyyy)
     * and a "to" time (in dd/MM/yyyy)
     *
     * @param description the description of the event
     * @param beginningDate the beginning time in (in dd/MM/yyyy) format
     * @param endingDate the ending time in (in dd/MM/yyyy) format
     */
    public Events(String description, LocalDate beginningDate, LocalDate endingDate) {
        super(description);
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
    }

    /**
     * This is an Events constructor that takes in a description, a "from" time (in HH:mm)
     * and a "to" time (in HH:mm)
     *
     * @param description the description of the event
     * @param beginningTime the beginning time in (in HH:mm) format
     * @param endingTime the ending time in (in HH:mm) format
     */
    public Events(String description, LocalTime beginningTime, LocalTime endingTime) {
        super(description);
        this.beginningTime = beginningTime;
        this.endingTime = endingTime;
    }

    /**
     * prints out the final display of the event details to the user.
     * @return prints out the final display of the event details to the user.
     */
    @Override
    public String finalString() {
        if (beginningDateTime != null) {
            return "[E]" + super.finalString() + " (from: " + beginningDateTime.format(dateTimeFormatter) + " to: "
                    + endingDateTime.format(dateTimeFormatter) + ")";
        } else if (beginningDate != null) {
            return "[E]" + super.finalString() + " (from: " + beginningDate.format(dateFormatter) + " to: "
                    + endingDate.format(dateFormatter) + ")";
        } else if (beginningTime != null) {
            return "[E]" + super.finalString() + " (from: " + beginningTime.format(timeFormatter) + " to: "
                    + endingTime.format(timeFormatter) + ")";
        } else {
            return "[E]" + super.finalString() + " (from: " + from + " to: " + to + ")";
        }
    }

    /**
     * prints out the final display of the event details to the file to be stored.
     * @return prints out the final display of the event details to the file to be stored.
     */
    @Override
    public String toFileFormat() {
        String fromToTimeString;

        if (beginningDateTime != null) {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + beginningDateTime.format(dateTimeFormatter) + "|" + endingDateTime.format(dateTimeFormatter);
        } else if (beginningDate != null) {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + beginningDate.format(dateFormatter) + "|" + endingDate.format(dateFormatter);
        } else if (beginningTime != null) {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|"
                    + beginningTime.format(timeFormatter) + "|" + endingTime.format(timeFormatter);
        } else {
            fromToTimeString = "E|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + from + "|" + to;
        }
        return fromToTimeString;
    }
}
