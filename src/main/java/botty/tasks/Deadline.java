package botty.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;
import botty.exceptions.IncorrectDateFormatException;

/**
 * A task with an end date
 */
public class Deadline extends Task<DeadlineData> {
    // The end date of the task
    private LocalDate endDate;

    /**
     * Constructs a {@code Deadline} with the given inputs
     * @param isCompleted whether the task is completed
     * @param data the data involved in generating the {@code Deadline}
     * @throws EmptyArgumentException if the description or end date is empty
     * @throws IncorrectDateFormatException if the end date is formatted incorrectly
     */
    public Deadline(boolean isCompleted, DeadlineData data)
            throws EmptyArgumentException, IncorrectDateFormatException {
        super(isCompleted, data);
        if (!data.hasEndDate()) {
            throw new EmptyArgumentException("end date");
        }
        try {
            this.endDate = LocalDate.parse(data.getEndDate());
        } catch (DateTimeParseException ex) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }

    /**
     * Constructs a {@code Deadline} with the given inputs, set to not completed
     * @param data the data involved in generating the {@code Deadline}
     * @throws EmptyArgumentException if the description or end date is empty
     * @throws IncorrectDateFormatException if the end date is formatted incorrectly
     */
    public Deadline(DeadlineData data) throws EmptyArgumentException, IncorrectDateFormatException {
        this(false, data);
    }

    /**
     * Returns a string representation of the {@code Deadline}
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Constructs a {@code Deadline} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed {@code Deadline}
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Deadline fromDataString(String taskString) throws BottyException {
        String standardDataStorageFormatRegex = "D \\| [10] \\| (.*?) \\| (.*?)";
        if (!taskString.matches(standardDataStorageFormatRegex)) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean isCompleted = arguments[1].equals("1");
        String description = arguments[2];
        String endDate = arguments[3];

        return new Deadline(isCompleted, new DeadlineData(description, endDate));
    }

    /**
     * Returns a string representation for storage in local storage
     */
    @Override
    public String toDataString() {
        return "D | " + getCompletedAndDescription() + " | " + endDate;
    }

    /**
     * Returns the task type of the task
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Updates the task with the given data
     */
    @Override
    public void update(DeadlineData data) throws BottyException {
        try {
            super.update(data);
            endDate = data.hasEndDate() ? LocalDate.parse(data.getEndDate()) : endDate;
        } catch (DateTimeParseException e) {
            throw new IncorrectDateFormatException("end date needs to be in format yyyy-mm-dd");
        }
    }
}
