package gravitas.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gravitas.exception.DukeException;



/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the deadline task.
     * @param endDate     End date of the deadline task.
     * @throws DukeException If the date and time format is invalid.
     */
    public Deadline(String description, String endDate) throws DukeException {
        super(description, "D");

        try {
            String[] EndTimeArr = endDate.split(" ", 2);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localEndDate = LocalDate.parse(EndTimeArr[0], dateFormatter);
            String formattedEndTime = EndTimeArr[1].substring(0, 2) + ":" + EndTimeArr[1].substring(2, 4);

            this.endDate = localEndDate;
            this.endTime = LocalTime.parse(formattedEndTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time.");
        }
    }

    @Override
    public String getDescription() {
        String formatEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmm a");

        return super.description + " (by: " + formatEndDate + " " + this.endTime.format(timeFormatter) + ")";
    }

    @Override
    public String formatData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String mark = this.isDone ? "1" : "0";

        return (this.eventType + " | " + mark + " | " + this.description
                + " | " + this.endDate.format(dateFormatter) + " | "
                + this.endTime.format(timeFormatter) + "\n");
    }
}
