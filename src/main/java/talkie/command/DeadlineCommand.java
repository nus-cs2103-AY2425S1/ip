package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Deadline;
import talkie.task.Task;
import talkie.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a deadline task to the task list in the Talkie application.
 * The command parses the user input to create a {@code Deadline} task with a description and a specific deadline date and time.
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
     * If the input is invalid, such as a missing description or an incorrectly formatted date/time,
     * an appropriate error message is displayed.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @throws TalkieMissingArgumentException If the command is missing a description or deadline.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                String details = parts[1]; // rest of the input (eg. from, to details)
                String[] deadlineParts = details.split("/by ");
                String description = deadlineParts[0].trim();
                String by = deadlineParts[1].trim();

                LocalDateTime time = LocalDateTime.parse(by, formatter);

                Task newDeadline = new Deadline(description, time);
                tasks.addTask(newDeadline);
                ui.addMessage(newDeadline, tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description' and 'by' of deadline cannot be empty.");
            }
        } catch (DateTimeParseException e) {
            ui.wrongDateTimeFormatMessage();
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
