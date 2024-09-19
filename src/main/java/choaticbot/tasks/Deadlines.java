package choaticbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import choaticbot.exceptions.WrongInputFormatException;

/**
 * The {@code Deadlines} class represents a task with a specific deadline.
 * It extends the {@link Task} class and includes a {@link LocalDateTime} field to store the deadline.
 */
public class Deadlines extends Task {

    /**
     * The date and time format used for parsing the input deadline string.
     */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The date format used for displaying the deadline.
     */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * The {@link LocalDateTime} representing the deadline.
     */
    private LocalDateTime deadline;

    /**
     * Constructs a {@code Deadlines} object with the specified task name and deadline string.
     * The deadline string is parsed using the input format {@code yyyy-MM-dd HH:mm}.
     *
     * @param name The name of the task.
     * @param deadlineString The deadline string in the format {@code yyyy-MM-dd HH:mm}.
     */
    public Deadlines(String name, String deadlineString) throws WrongInputFormatException {
        super(name);
        try {
            this.deadline = LocalDateTime.parse(deadlineString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new WrongInputFormatException("Expected format example: Read a book /by 2024-01-31 18:00");
        }
    }

    /**
     * Returns the type of the task, which is "D" for deadlines.
     *
     * @return The string "D" representing the type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns additional information about the task, specifically the formatted deadline date.
     *
     * @return The formatted deadline string in the format {@code MMM d yyyy}.
     */
    @Override
    public String getAdditionalInfo() {
        return deadline.format(OUTPUT_FORMAT);
    }

    /**
     * Converts the task to a string that can be written to a file.
     * This string includes the type, name, completion status, and deadline.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    /**
     * Updates information about the task, such as description or times.
     *
     * @param details A string containing task information to update.
     * @throws WrongInputFormatException If the user input is of wrong format.
     */
    @Override
    public void update(String details) throws WrongInputFormatException {
        String[] deadlineDetails = details.split("/by ");
        if (deadlineDetails.length != 2) {
            throw new WrongInputFormatException("Expected format example: Read a book /by 2024-01-31 18:00");
        }

        try {
            this.deadline = LocalDateTime.parse(deadlineDetails[1], INPUT_FORMAT);
            super.changeName(deadlineDetails[0]);
        } catch (DateTimeParseException e) {
            throw new WrongInputFormatException("Expected format example: Read a book /by 2024-01-31 18:00");
        }
    }

    /**
     * Returns the deadline as a string in the format {@code yyyy-MM-dd HH:mm},
     * which is suitable for saving to a file.
     *
     * @return The deadline string in the format {@code yyyy-MM-dd HH:mm}.
     */
    public String getDeadline() {
        String deadlineString = deadline.toString();
        String[] deadlineToSaveArray = deadlineString.split("T");

        return deadlineToSaveArray[0] + " " + deadlineToSaveArray[1];
    }

    /**
     * Returns the string representation of the task, including its type, name, and deadline.
     *
     * @return A string representing the task in the format {@code [D] task_name (MMM d yyyy)}.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "(" + deadline.format(OUTPUT_FORMAT) + ")";
    }
}
