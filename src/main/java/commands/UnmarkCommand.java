package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;
import utility.CommandUtility;

/**
 * Represents an unmark command entered by the user.
 */
public class UnmarkCommand extends Command {
    /**
     * Stores the command string associated with unmark command.
     *
     * @param command Command string.
     */
    public UnmarkCommand(String command) {
        super(command);
    }

    /**
     * Checks if the unmark command is valid.
     *
     * @param tasks List of current {@code Task} objects.
     * @throws BrockException If the unmark command is invalid.
     */
    private void validateUnmark(TaskList tasks) throws BrockException {
        String[] commandWords = this.processCommand();
        CommandUtility.validateLength(commandWords, "Unmark ");
        CommandUtility.validateTaskNumber(commandWords, tasks);
    }

    /**
     * Update the save file to reflect the unmarked task.
     *
     * @param taskStorage Instance that interfaces with the save file.
     * @param tasks List of current {@code Task} objects.
     * @throws BrockException If writing to save file fails.
     */
    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks) throws BrockException {
        String tasksString = tasks.listTasks();
        taskStorage.writeToFile("", false);
        taskStorage.writeToFile(tasksString, true);
    }

    /**
     * Gets the chatbot response to the unmark command.
     *
     * @param tasks List of current {@code Task} objects.
     * @param taskIndex Index of the unmarked task.
     * @return Chatbot response.
     */
    private String getResponse(TaskList tasks, int taskIndex) {
        return "OK, I've marked this task as not done yet:\n"
                + "  " + tasks.getTaskDetails(taskIndex);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if unmark command is valid.
     * If so, it unmarks the associated task in {@code tasks} and updates the save file.
     * Returns a response indicating it has successfully unmarked the task.
     * </p>
     *
     * @throws BrockException If unmark command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        this.validateUnmark(tasks);

        String command = super.getCommand();
        int taskIndex = CommandUtility.getTaskIndex(command);

        boolean isSuccessful = tasks.unmarkTask(taskIndex);
        if (!isSuccessful) {
            throw new BrockException("Task has not been marked yet!");
        }

        tempStorage.setLastToggledTaskNum(taskIndex + 1);
        this.updateSaveFile(taskStorage, tasks);
        return this.getResponse(tasks, taskIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "unmark";
    }
}
