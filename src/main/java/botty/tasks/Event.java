package botty.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.IncorrectDateFormatException;

/**
 * A task with a start date and an end date
 */
public class Event extends Task<EventData> {
    // The start date of the task
    private LocalDate startDate;
    // The end date of the task
    private LocalDate endDate;

    /**
     * Constructs an {@code Event} with the given arguments
     * @param isCompleted if the {@code Event} is completed
     * @param data the data involved in generating the {@code Event}
     * @throws EmptyArgumentException if description, start date or end date is empty
     * @throws IncorrectDateFormatException if start date or end date is in the incorrect date format
     */
    public Event(boolean isCompleted, EventData data)
            throws EmptyArgumentException, IncorrectDateFormatException {
        super(isCompleted, data);
        if (!data.hasStartDate()) {
            throw new EmptyArgumentException("start date");
        }
        if (!data.hasEndDate()) {
            throw new EmptyArgumentException("end date");
        }
        try {
            this.startDate = LocalDate.parse(data.getStartDate());
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("start date needs to be in format yyyy-mm-dd");
        }
        try {
            this.endDate = LocalDate.parse(data.getEndDate());
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }
    /**
     * Constructs an {@code Event} with the given arguments, set to not completed
     * @param data the data involved in generating the {@code Event}
     * @throws EmptyArgumentException if description, start date or end date is empty
     * @throws IncorrectDateFormatException if start date or end date is in the incorrect date format
     */
    public Event(EventData data)
            throws EmptyArgumentException, IncorrectDateFormatException {
        this(false, data);
    }

    /**
     * Returns a string representation of the event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Constructs an {@code Event} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed event
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Event fromDataString(String taskString) throws BottyException {
        String standardDataStorageFormatRegex = "E \\| [10] \\| (.*?) \\| (.*?)";
        if (!taskString.matches(standardDataStorageFormatRegex)) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];
        String startDate = arguments[3];
        String endDate = arguments[4];

        return new Event(completed, new EventData(description, startDate, endDate));
    }

    /**
     * Returns a string representation of the {@code Event} that is used for local storage
     */
    @Override
    public String toDataString() {
        return "E | " + getCompletedAndDescription() + " | " + startDate + " | " + endDate;
    }

    /**
     * Returns the task type of the task
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Updates the task with the given data
     */
    @Override
    public void update(EventData data) throws BottyException {
        try {
            super.update(data);
            startDate = data.hasStartDate() ? LocalDate.parse(data.getStartDate()) : startDate;
            endDate = data.hasEndDate() ? LocalDate.parse(data.getEndDate()) : endDate;
        } catch (DateTimeParseException e) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }

}
