package tasks;

import exceptions.CorruptedTaskStringException;
import exceptions.EmptyArgumentException;

public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    public Event(boolean completed, String description, String startDateTime, String endDateTime)
            throws EmptyArgumentException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (startDateTime.isEmpty()) {
            throw new EmptyArgumentException("start date time");
        }
        if (endDateTime.isEmpty()) {
            throw new EmptyArgumentException("end date time");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
    public Event(String description, String startDateTime, String endDateTime)
            throws EmptyArgumentException {
        this(false, description, startDateTime, endDateTime);
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    public static Event fromDataString(String taskString) throws CorruptedTaskStringException, EmptyArgumentException {
        if (!taskString.matches("E \\| [10] \\| (.*?) \\| (.*?) \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String startDateTime = arguments[3];
        String endDateTime = arguments[4];

        return new Event(completed, description, startDateTime, endDateTime);
    }

    @Override
    public String toDataString() {
        return "E | " + getCompletedAndDescription() + " | " + startDateTime + " | " + endDateTime;
    }
}
