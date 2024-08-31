package tasks;

import exceptions.CorruptedTaskStringException;
import exceptions.EmptyArgumentException;

public class Deadline extends Task {
    private final String endDateTime;

    public Deadline(boolean completed, String description, String endDateTime) throws EmptyArgumentException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (endDateTime.isEmpty()) {
            throw new EmptyArgumentException("end date time");
        }
        this.endDateTime = endDateTime;
    }
    public Deadline(String description, String endDateTime) throws EmptyArgumentException {
        this(false, description, endDateTime);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDateTime + ")";
    }

    public static Deadline fromDataString(String taskString) throws CorruptedTaskStringException, EmptyArgumentException {
        if (!taskString.matches("D \\| [10] \\| (.*?) \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String endDateTime = arguments[3];

        return new Deadline(completed, description, endDateTime);
    }

    @Override
    public String toDataString() {
        return "D | " + getCompletedAndDescription() + " | " + endDateTime;
    }
}
