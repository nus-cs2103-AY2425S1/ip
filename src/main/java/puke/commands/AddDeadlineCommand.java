package puke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import puke.TaskList;
import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.MissingDeadlineTimeException;
import puke.exceptions.WrongDateTimeFormatException;
import puke.message.MessageBuilder;

/**
 * Command to add a new Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private String description;
    private String by;

    /**
     * Constructs a new AddDeadlineCommand with specified arguments.
     *
     * @param args the string containing the task description followed by '/by' and the deadline time.
     * @throws EmptyDescriptionException    if the task description is empty.
     * @throws MissingDeadlineTimeException         if the deadline time is missing or empty.
     * @throws WrongDateTimeFormatException if the deadline time does not match the required format.
     */
    public AddDeadlineCommand(String args) throws EmptyDescriptionException, MissingDeadlineTimeException,
            WrongDateTimeFormatException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] parts = args.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingDeadlineTimeException();
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();

        // Validate the 'by' datetime format
        validateDateTimeFormat(this.by);
    }

    /**
     * Validates that the provided datetime string matches the required format.
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
     * Executes the add deadline command.
     * This method adds a new deadline task to the task list and sends a confirmation message
     * using the message builder.
     *
     * @param taskList the task list to which the deadline task will be added.
     * @param messageBuilder the message builder used to generate and send the confirmation message.
     * @return the confirmation message after adding the deadline task.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) {
        return messageBuilder.sendMessage(taskList.addTask("deadline", description, by));
    }
}

