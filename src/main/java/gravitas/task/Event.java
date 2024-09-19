package gravitas.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gravitas.exception.GravitasException;

/**
 * Represents a Event task.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the event task.
     * @param startDate   Start date of the event task.
     * @param endDate     End date of the event task.
     * @throws GravitasException If the date and time format is invalid.
     */
    public Event(String description, String startDate, String endDate) throws GravitasException {
        super(description, "E");

        try {
            String[] startTimes = startDate.split(" ", 2);
            String[] endTimes = endDate.split(" ", 2);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localStartDate = LocalDate.parse(startTimes[0], dateFormatter);
            LocalDate localEndDate = LocalDate.parse(endTimes[0], dateFormatter);
            String formattedStartTime = startTimes[1].substring(0, 2) + ":"
                    + startTimes[1].substring(2, 4);
            String formattedEndTime = endTimes[1].substring(0, 2) + ":" + endTimes[1].substring(2, 4);
            this.startDate = localStartDate;
            this.endDate = localEndDate;
            this.startTime = LocalTime.parse(formattedStartTime);
            this.endTime = LocalTime.parse(formattedEndTime);
        } catch (DateTimeParseException e) {
            throw new GravitasException("Please enter a valid date and time.");
        }
    }

    @Override
    public String getDescription() {
        String formatStartDate = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmm a");
        return super.description + " (From: " + formatStartDate + " "
                + this.startTime.format(timeFormatter) + " To: " + formatEndDate + " "
                + this.endTime.format(timeFormatter) + ")";
    }

    @Override
    public String formatData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String mark = this.isDone ? "1" : "0";
        return (this.eventType + " | " + mark + " | " + this.description
                + " | " + this.startDate.format(dateFormatter) + " | "
                + this.startTime.format(timeFormatter) + " | "
                + this.endDate.format(dateFormatter) + " | "
                + this.endTime.format(timeFormatter) + "\n");
    }
}
