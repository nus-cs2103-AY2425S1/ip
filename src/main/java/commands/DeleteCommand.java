package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.Task;
import task.TaskList;
import utility.CommandUtility;

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
        String[] commandWords = this.processCommand();
        CommandUtility.validateLength(commandWords, "Delete ");
        CommandUtility.validateTaskNumber(commandWords, tasks);
    }

    /**
     * Removes the deleted task from save file.
     *
     * @param taskStorage Instance that interfaces with save file.
     * @param tasks List of current {@code Task} objects.
     * @throws BrockException If writing to file fails.
     */
    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks) throws BrockException {
        String remainingTasks = tasks.listTasks();
        taskStorage.writeToFile("", false);
        taskStorage.writeToFile(remainingTasks, true);
    }

    /**
     * Gets the chatbot response to delete command.
     *
     * @param tasks List of current {@code Task} objects.
     * @param deletedTaskDetails Details of the deleted task.
     * @return Chatbot response.
     */
    private String getResponse(TaskList tasks, String deletedTaskDetails) {
        return "Noted. I've removed this task:\n"
                + "  " + deletedTaskDetails + '\n'
                + tasks.getTasksSummary()
                + this.getQuirkyResponse();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if delete command is valid.
     * If so, it deletes the corresponding task from {@code tasks}, updates the save file.
     * As well as return a response indicating successful deletion.
     * </p>
     *
     * @throws BrockException If delete command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        this.validateDelete(tasks);

        String command = super.getCommand();
        int taskIndex = CommandUtility.getTaskIndex(command);
        Task deletedTask = tasks.getTask(taskIndex);
        String deletedTaskDetails = tasks.getTaskDetails(taskIndex);
        tasks.removeFromList(taskIndex);

        tempStorage.setLastDeletedTask(deletedTask);
        this.updateSaveFile(taskStorage, tasks);
        return this.getResponse(tasks, deletedTaskDetails);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "delete";
    }
}
