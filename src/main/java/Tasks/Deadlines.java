package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private final LocalDate dueDate;
    private final LocalTime dueTime;

    private LocalTime parseTime(String dueTimeString) {
        String hours = dueTimeString
                .substring(0, 2);
        String minutes = dueTimeString
                .substring(2);
        return LocalTime.of(Integer.parseInt(hours)
                , Integer.parseInt(minutes));
    }

    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (this.dueDate.isBefore(today)) {
            throw new BrockException("Due date cannot be earlier than today!");
        }
        if (this.dueTime != LocalTime.MAX) {
            LocalTime now = LocalTime.now();
            if (this.dueTime.isBefore(now)) {
                throw new BrockException("Due time cannot be earlier than now!");
            }
        }
    }

    public Deadlines(String description, String dueDateString) throws BrockException {
        super(description);
        try {
            this.dueTime = LocalTime.MAX; // dummy value for time
            this.dueDate = LocalDate.parse(dueDateString);
            validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
        }
    }

    public Deadlines(String description, String dueDateString, String dueTimeString) throws BrockException {
        super(description);
        try {
            this.dueTime = parseTime(dueTimeString);
            this.dueDate = LocalDate.parse(dueDateString);
            validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public String getExtraInfo() {
        String dueDateFormatted = this.dueDate
                .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "(by: " + dueDateFormatted
                + (this.dueTime == LocalTime.MAX
                ? ""
                : ", " + this.dueTime.toString())
                + ")";
    }
}
