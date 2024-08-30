package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieInvalidArgumentException;
import talkie.exception.TalkieMissingArgumentException;
import talkie.exception.TalkieNoTaskFoundException;
import talkie.task.Task;
import talkie.task.TaskList;

/**
 * Represents a command to delete a task from the task list in the Talkie application.
 * The command parses the user input to determine which task to delete based on its index in the task list.
 */
public class DeleteCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a new {@code DeleteCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type and the index of the task to be deleted.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code DeleteCommand} by parsing the input to delete the specified task from the task list.
     * If the input is invalid, such as a missing or incorrect task index, appropriate exceptions are thrown.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @throws TalkieMissingArgumentException  If the command is missing the required task index.
     * @throws TalkieNoTaskFoundException      If the task index specified does not exist in the task list.
     * @throws TalkieInvalidArgumentException  If the provided argument is not a valid integer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieMissingArgumentException, TalkieNoTaskFoundException, TalkieInvalidArgumentException {
        String[] temp = this.fullCommand.split(" ");

        // Check if user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'delete' command requires an integer as argument");

        // Check if user included the correct int argument
        } else if (this.isInteger(temp[1])) {
            int index = Integer.parseInt(this.fullCommand.split(" ")[1]);

            // Check if the task is in the list
            if (index <= tasks.size()) {
                Task task = tasks.deleteTask(index);
                ui.deleteMessage(task, tasks.size());
            } else {
                throw new TalkieNoTaskFoundException();
            }
        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'delete' command requires an integer as argument");
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
     * Checks if a given input string can be parsed as an integer.
     *
     * @param input The string to be checked.
     * @return {@code true} if the input can be parsed as an integer; {@code false} otherwise.
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
