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

    private String fullCommand;

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
     * <p>
     * The command expects a description and a deadline in the format:
     * <code>deadline description /by yyyy-MM-dd HHmm</code>. If the description or deadline is missing,
     * or if the date/time format is incorrect, appropriate exceptions are thrown, and error messages are displayed.
     * </p>
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
        String[] parts = fullCommand.split(" ", 2); // Split into type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                String details = parts[1]; // Rest of the input (e.g., description /by deadline details)
                String[] deadlineParts = details.split("/by ");
                String description = deadlineParts[0].trim();
                String by = deadlineParts[1].trim();

                LocalDateTime time = LocalDateTime.parse(by, formatter);

                Task newDeadline = new Deadline(description, time);
                tasks.addTask(newDeadline);
                return ui.addMessage(newDeadline, tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description' and 'by' of deadline cannot be empty.");
            }
        } catch (DateTimeParseException e) {
            return ui.wrongDateTimeFormatMessage();
        }
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
