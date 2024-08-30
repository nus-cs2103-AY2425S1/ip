package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieInvalidArgumentException;
import talkie.exception.TalkieMissingArgumentException;
import talkie.exception.TalkieNoTaskFoundException;
import talkie.task.Task;
import talkie.task.TaskList;

/**
 * Represents a command to mark a task as not done in the Talkie application.
 * The command reverts the completion status of a specified task.
 */
public class UnMarkCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a new {@code UnMarkCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type and task index.
     */
    public UnMarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code UnMarkCommand} by marking a specified task as not done.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @throws TalkieInvalidArgumentException If the argument provided is not a valid integer.
     * @throws TalkieMissingArgumentException If no task index is provided.
     * @throws TalkieNoTaskFoundException     If the specified task does not exist in the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieInvalidArgumentException, TalkieMissingArgumentException, TalkieNoTaskFoundException {
        String[] temp = fullCommand.split(" ");

        // Check if the user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'unmark' command requires an integer as argument");

            // Check if the user included the correct integer argument
        } else if (this.isInteger(temp[1])) {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);

            // Check if the task index is valid in the task list
            if (index <= tasks.size()) {
                Task task = tasks.getTask(index);
                task.markAsNotDone();
                ui.unMarkMessage(task);
            } else {
                throw new TalkieNoTaskFoundException();
            }

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'unmark' command requires an integer as argument");
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
