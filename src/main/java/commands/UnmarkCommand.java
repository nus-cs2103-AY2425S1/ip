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

    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks) throws BrockException {
        String tasksString = tasks.listTasks();
        taskStorage.writeToFile("", false);
        taskStorage.writeToFile(tasksString, true);
    }

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
        String command = super.getCommand();
        CommandUtility.validateStatus(command, CommandUtility.Action.UNMARK, tasks);
        int taskIndex = CommandUtility.getTaskIndex(command);

        boolean isSuccessful = tasks.unmarkTask(taskIndex);
        if (!isSuccessful) {
            throw new BrockException("Task has not been marked yet!");
        }

        tempStorage.setPreviousCommand("unmark");
        tempStorage.setLastToggledTaskNum(taskIndex + 1);

        this.updateSaveFile(taskStorage, tasks);
        return this.getResponse(tasks, taskIndex);
    }
}
