package botty.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.IncorrectDateFormatException;

public class Deadline extends Task {
    private final LocalDate endDate;

    public Deadline(boolean completed, String description, String endDate)
            throws EmptyArgumentException, IncorrectDateFormatException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (endDate.isEmpty()) {
            throw new EmptyArgumentException("end date");
        }
        try {
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }
    public Deadline(String description, String endDate) throws EmptyArgumentException, IncorrectDateFormatException {
        this(false, description, endDate);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public static Deadline fromDataString(String taskString) throws BottyException {
        if (!taskString.matches("D \\| [10] \\| (.*?) \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String endDate = arguments[3];

        return new Deadline(completed, description, endDate);
    }

    @Override
    public String toDataString() {
        return "D | " + getCompletedAndDescription() + " | " + endDate;
    }
}
