package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieInvalidArgumentException;
import talkie.exception.TalkieMissingArgumentException;
import talkie.exception.TalkieNoTaskFoundException;
import talkie.task.Task;
import talkie.task.TaskList;

/**
 * Represents a command to mark a task as done in the Talkie application.
 * The command marks a specified task as completed.
 */
public class MarkCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a new {@code MarkCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type and task index.
     */
    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code MarkCommand} by marking a specified task as done.
     * <p>
     * The method parses the command input to extract the task index and then marks the corresponding task in the
     * task list as done. A confirmation message is generated to inform the user of the successful operation.
     * If the index is not provided or is invalid, appropriate exceptions are thrown.
     * </p>
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @return A string containing a confirmation message about the task being marked as done, including the
     *         task details.
     * @throws TalkieInvalidArgumentException If the argument provided is not a valid integer or is not in the expected
     *         format.
     * @throws TalkieMissingArgumentException If no task index is provided in the command input.
     * @throws TalkieNoTaskFoundException     If the specified task index does not correspond to any task in the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieInvalidArgumentException, TalkieMissingArgumentException, TalkieNoTaskFoundException {
        String[] temp = fullCommand.split(" ");

        // Check if the user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'mark' command requires an integer as argument");

            // Check if the user included the correct integer argument
        } else if (this.isInteger(temp[1])) {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);

            // Check if the task is in the list
            if (index <= tasks.size()) {
                Task task = tasks.getTask(index);
                task.markAsDone();
                return ui.markMessage(task);
            } else {
                throw new TalkieNoTaskFoundException();
            }

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'mark' command requires an integer as argument");
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

    /**
     * Checks if a given string can be parsed as an integer.
     *
     * @param input The string to check.
     * @return {@code true} if the string can be parsed as an integer, {@code false} otherwise.
     */
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
