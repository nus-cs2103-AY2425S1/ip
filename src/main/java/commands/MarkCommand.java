package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;
import utility.CommandUtility;

/**
 * Represents a mark command entered by the user.
 */
public class MarkCommand extends Command {
    /**
     * Stores the command string associated with mark command.
     *
     * @param command Command string.
     */
    public MarkCommand(String command) {
        super(command);
    }

    /**
     * Checks if the mark command is valid.
     *
     * @param tasks List of current {@code Task} objects.
     * @throws BrockException If mark command is invalid.
     */
    private void validateMark(TaskList tasks) throws BrockException {
        String[] commandWords = this.processCommand();
        CommandUtility.validateLength(commandWords, "Mark ");
        CommandUtility.validateTaskNumber(commandWords, tasks);
    }

    /**
     * Updates the save file to reflect the marked task.
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
     * Gets the chatbot response to the mark command.
     *
     * @param tasks List of current {@code Task} objects.
     * @param taskIndex Index of the marked task.
     * @return Chatbot response.
     */
    private String getResponse(TaskList tasks, int taskIndex) {
        return "Nice! I've marked this task as done:\n"
                + "  " + tasks.getTaskDetails(taskIndex);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if mark command is valid.
     * If so, it marks the associated task in {@code tasks} and updates the save file.
     * Returns a response indicating it has successfully marked the task.
     * </p>
     *
     * @throws BrockException If mark command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        this.validateMark(tasks);

        String command = super.getCommand();
        int taskIndex = CommandUtility.getTaskIndex(command);

        boolean isSuccessful = tasks.markTask(taskIndex);
        if (!isSuccessful) {
            throw new BrockException("Task has been marked already!");
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
        return "mark";
    }
}
