package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private final LocalDate DUE_DATE;
    private final LocalTime DUE_TIME;

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
        if (DUE_DATE.isBefore(today)) {
            throw new BrockException("Due date cannot be earlier than today!");
        }
        if (DUE_TIME != LocalTime.MAX) {
            LocalTime now = LocalTime.now();
            if (DUE_TIME.isBefore(now)) {
                throw new BrockException("Due time cannot be earlier than now!");
            }
        }
    }

    public Deadlines(String description, String dueDateString) throws BrockException {
        super(description);
        try {
            DUE_TIME = LocalTime.MAX; // dummy value for time
            DUE_DATE = LocalDate.parse(dueDateString);
            validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
        }
    }

    public Deadlines(String description, String dueDateString, String dueTimeString) throws BrockException {
        super(description);
        try {
            DUE_TIME = parseTime(dueTimeString);
            DUE_DATE = LocalDate.parse(dueDateString);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String dueDateFormatted = DUE_DATE
                .format(formatter);
        return "(by: " + dueDateFormatted
                + (DUE_TIME == LocalTime.MAX
                ? ""
                : ", " + DUE_TIME.toString())
                + ")";
    }
}
