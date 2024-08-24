import exceptions.MissingParametersException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task{
    private LocalDateTime by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * @param description The Description of the Deadline with /by to state the dateline
     */
    public Deadlines(String description, String by) throws MissingParametersException {
        super(description);
        this.by = parseDateTime(by);

    }

    /**
     * Alternate constructor for loading isDone directly
     * @param isDone 1 or 0 indicating if task is done
     * @param description The Description of the Deadline
     * @param by by to state the dateline of the task
     */
    public Deadlines(String isDone, String description, String by) throws MissingParametersException {
        super(isDone, description);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) throws MissingParametersException {
        try {
            return this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new MissingParametersException("deadline", "deadline return book /by 2/12/2019 1800");
        }
    }

    private String printDateTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDateTime() + ")";
    }

    /**
     * Returns the deadlines date
     * @return deadline's date
     */
    public LocalDate getDate() {
        return this.by.toLocalDate();
    }

    /**
     * A string that matches the format for writing it to file
     * @return A string to be written to a txt file
     */
    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by.format(formatter);
    }
}
