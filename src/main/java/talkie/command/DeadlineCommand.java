package talkie.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Deadline;
import talkie.task.Task;
import talkie.task.TaskList;

/**
 * Represents a command to add a deadline task to the task list in the Talkie application.
 * The command parses the user input to create a {@code Deadline} task with a description
 * and a specific deadline date and time.
 */
public class DeadlineCommand extends Command {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private final String fullCommand;

    /**
     * Constructs a new {@code DeadlineCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type and details of the deadline.
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code DeadlineCommand} by parsing the input to create a new {@code Deadline} task,
     * adding it to the task list, and displaying an appropriate message to the user.
     *
     * @param tasks   The {@code TaskList} containing all current tasks.
     * @param ui      The {@code Ui} component used to display messages to the user.
     * @param storage The {@code Storage} component used to save task data.
     * @return A string containing a confirmation message about the added deadline task, or an error message if the
     *         input is invalid.
     * @throws TalkieMissingArgumentException If the command is missing the required description or deadline.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        try {
            String[] parts = splitCommand();
            validateParts(parts);

            String[] deadlineParts = splitDeadlineParts(parts[1]);
            String description = deadlineParts[0].trim();
            LocalDateTime time = parseDateTime(deadlineParts[1].trim());

            Task newDeadline = new Deadline(description, time);
            tasks.addTask(newDeadline);
            return ui.addMessage(newDeadline, tasks.size());
        } catch (DateTimeParseException e) {
            return ui.wrongDateTimeFormatMessage();
        }
    }

    private String[] splitCommand() {
        return fullCommand.split(" ", 2);
    }

    private void validateParts(String[] parts) throws TalkieMissingArgumentException {
        if (parts.length != 2) {
            throw new TalkieMissingArgumentException(parts[0],
                    "The 'description' and 'by' of deadline cannot be empty.");
        }
    }

    private String[] splitDeadlineParts(String details) throws TalkieMissingArgumentException {
        String[] deadlineParts = details.split("/by ");
        if (deadlineParts.length != 2) {
            throw new TalkieMissingArgumentException("deadline",
                    "The deadline must be in the format: description /by yyyy-MM-dd HHmm");
        }
        return deadlineParts;
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return {@code false}, as this command does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
