package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private final LocalDate dueDate;

    // TODO: Probably change this to LocalTime for intuitive
    private final String time;

    private LocalTime parseTime() {
        String hours = this.time
                .substring(0, 2);
        String minutes = this.time
                .substring(2);
        return LocalTime.of(Integer.parseInt(hours)
                , Integer.parseInt(minutes));
    }

    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (this.dueDate.isBefore(today)) {
            throw new BrockException("Due date cannot be earlier than today!");
        }
        if (!this.time.isEmpty()) {
            LocalTime now = LocalTime.now();
            if (parseTime().isBefore(now)) {
                throw new BrockException("Due time cannot be earlier than now!");
            }
        }
    }

    public Deadlines(String description, String dueDateString) throws BrockException {
        super(description);
        try {
            this.time = "";
            this.dueDate = LocalDate.parse(dueDateString);
            validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
        }
    }

    public Deadlines(String description, String dueDateString, String dueTimeString) throws BrockException {
        super(description);
        try {
            this.time = dueTimeString;
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
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "(by: " + dueDateFormatted
                + (this.time.isEmpty()
                ? ""
                : ", " + this.time)
                + ")";
    }
}
