package commands;

import exceptions.BrockException;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import utility.Utility;

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
     * @param tasks List of current {@code Task} objects.
     * @throws BrockException If command is missing a task number, has a wrong task number.
     *      Or, it is in the wrong format altogether.
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
    public String execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        validateDelete(tasks);

        String command = super.getCommand();
        int taskIndex = Utility.getTaskIndex(command);
        String deletedTaskDetails = tasks.getTaskDetails(taskIndex);
        tasks.removeFromList(taskIndex);

        // Update the save file
        String remainingTasks = tasks.listTasks();
        storage.writeToFile("", false);
        storage.writeToFile(remainingTasks, true);

        return "Noted. I've removed this task:\n"
                + "  " + deletedTaskDetails + '\n'
                + tasks.getTasksSummary();
    }
}
