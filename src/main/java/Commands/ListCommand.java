package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

/**
 * Represents a list command entered by the user.
 */
public class ListCommand extends Command {

    /**
     * Stores the command string associated with list command.
     *
     * @param command Command string.
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot reads from save file, displaying all the existing tasks.
     * </p>
     *
     * @throws BrockException If save file is not found.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        String tasksString = storage.readFromFile();
        int totalTasks = tasks.numTasks();

        String responseBody;
        if (totalTasks == 0) {
            responseBody = "No current tasks!";
        } else {
            responseBody = tasksString;
        }

        ui.displayResponse((totalTasks == 1
                ? "Here is the task in your list:\n"
                : "Here are the tasks in your list:\n")
                + responseBody);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
