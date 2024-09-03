package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;
import Utility.Utility;

/**
 * Represents a delete command entered by the user.
 */
public class DeleteCommand extends Command {
    /**
     * Stores the command string associated with delete command.
     *
     * @param command Command string.
     */
    public DeleteCommand(String command) {
        super(command);
    }


    /**
     * Checks if the delete command is valid.
     *
     * @param tasks List of current task objects.
     * @throws BrockException If command is missing a task number, has a wrong task number.
     * Or, it is in the wrong format altogether.
     */
    private void validateDelete(TaskList tasks) throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!");
        }
        if (commandLength > 2 || Utility.isNotInteger(commandWords[1])) {
            throw new BrockException("Delete command is in the form delete <task-number>!");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = tasks.numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!");
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if delete command is valid.
     * If so, it deletes the corresponding task from {@code tasks}, updates the save file.
     * As well as displays a response indicating successful deletion.
     * </p>
     *
     * @throws BrockException If delete command is invalid.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        validateDelete(tasks);

        String command = super.getCommand();
        int targetIndex = Utility.getTargetIndex(command);
        tasks.removeFromList(targetIndex);

        ui.displayResponse("Noted. I've removed this task:\n"
                + "  "
                + tasks.getTaskDetails(targetIndex)
                + '\n'
                + tasks.getTasksSummary());

        // Update the save file
        String fileContents = storage.readFromFile(targetIndex + 1);
        storage.writeToFile("", false);
        storage.writeToFile(fileContents, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
