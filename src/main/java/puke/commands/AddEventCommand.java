package puke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import puke.TaskList;
import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.MissingEventTimeException;
import puke.exceptions.WrongDateTimeFormatException;
import puke.message.MessageBuilder;

/**
 * Command to add a new Event task.
 */
public class AddEventCommand extends Command {
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new {@code AddEventCommand} with the specified arguments.
     *
     * @param args the string containing the task description followed by '/from' for start time and '/to' for end time.
     *             The expected format is: "description /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm"
     * @throws EmptyDescriptionException    if the task description is empty.
     * @throws MissingEventTimeException   if either the start time (`/from`) or end time (`/to`) is missing or empty.
     * @throws WrongDateTimeFormatException if either the start time or end time does not match the
     *                                      required "dd/MM/yyyy HHmm" format.
     */
    public AddEventCommand(String args) throws EmptyDescriptionException, MissingEventTimeException,
            WrongDateTimeFormatException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        // Split the input based on " /from " and " /to " delimiters
        String[] parts = args.split(" /from | /to ");
        if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new MissingEventTimeException();
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();

        // Validate the 'from' and 'to' datetime formats
        validateDateTimeFormat(this.from);
        validateDateTimeFormat(this.to);
    }

    /**
     * Validates that the provided datetime string matches the required "dd/MM/yyyy HHmm" format.
     *
     * @param dateTimeStr the datetime string to validate.
     * @throws WrongDateTimeFormatException if the datetime string does not match the required format.
     */
    private void validateDateTimeFormat(String dateTimeStr) throws WrongDateTimeFormatException {
        try {
            LocalDateTime.parse(dateTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException(DATE_TIME_PATTERN);
        }
    }

    /**
     * Executes the add event command.
     * This method adds a new event task to the task list with a specified duration and sends a confirmation
     * message using the message builder.
     *
     * @param taskList the task list to add the event task to.
     * @param messageBuilder the message builder to generate and send the confirmation message.
     * @return the confirmation message after adding the task.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) {
        return messageBuilder.sendMessage(taskList.addTask("event", description, from, to));
    }
}

