package tasks;

import exceptions.BottyException;
import exceptions.IncorrectDateFormatException;
import exceptions.CorruptedTaskStringException;
import exceptions.EmptyArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Event(boolean completed, String description, String startDate, String endDate)
            throws EmptyArgumentException, IncorrectDateFormatException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (startDate.isEmpty()) {
            throw new EmptyArgumentException("start date");
        }
        if (endDate.isEmpty()) {
            throw new EmptyArgumentException("end date");
        }
        try {
            this.startDate = LocalDate.parse(startDate);
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("start date needs to be in format yyyy-mm-dd");
        }
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }
    public Event(String description, String startDateTime, String endDate)
            throws EmptyArgumentException, IncorrectDateFormatException {
        this(false, description, startDateTime, endDate);
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public static Event fromDataString(String taskString) throws BottyException {
        if (!taskString.matches("E \\| [10] \\| (.*?) \\| (.*?) \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String startDate = arguments[3];
        String endDate = arguments[4];

        return new Event(completed, description, startDate, endDate);
    }

    @Override
    public String toDataString() {
        return "E | " + getCompletedAndDescription() + " | " + startDate + " | " + endDate;
    }
}
