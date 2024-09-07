package mortalreminder.backend.tasklistmanager;

import mortalreminder.backend.TaskListStorage;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Task;

/**
 * Handles marking, unmarking or deleting tasks that have already been created.
 */
public class TaskEditor {

    /**
     * Marks, unmarks, or deletes a task based on the given command type.
     * <p>
     * This method attempts to parse the task index from the command details and then
     * performs the appropriate action (mark as done, unmark) on the task.
     * This method was optimised using ChatGPT.
     *
     * @param commandDetails the details of the command, typically the task index.
     * @param taskList       the {@link TaskList} containing the tasks to modify.
     * @param commandType    the {@link CommandType} indicating the action to perform.
     * @return returns a confirmation message of the corresponding type of command done.
     * @throws MortalReminderException if the index given is invalid (does not exist in the {@link TaskList}).
     */
    public static String executeMarkOrUnmark(String commandDetails, TaskList taskList, CommandType commandType)
            throws MortalReminderException {
        try {
            Task newTask = retrieveTask(commandDetails, taskList);
            String feedback;
            if (commandType == CommandType.MARK) {
                feedback = newTask.markDone();
            } else if (commandType == CommandType.UNMARK) {
                feedback = newTask.markUndone();
            } else {
                throw new MortalReminderException("This statement should be unreachable, code has an error!");
            }
            TaskListStorage.refreshStorageFile(taskList);
            return feedback;
        } catch (NumberFormatException e) {
            throw new MortalReminderException("Please enter a valid number after the command!");
        }
    }

    /**
     * Deletes a task from the app.
     *
     * @param commandDetails the unparsed integer string passed in by user.
     * @param taskList       the current list of tasks being tracked.
     * @return confirmation feedback about the deletion process
     * @throws MortalReminderException if there was an error in the deletion process.
     */
    public static String executeDeletion(String commandDetails, TaskList taskList) throws MortalReminderException {
        Task taskToDelete = retrieveTask(commandDetails, taskList);
        return taskList.deleteTask(taskToDelete);
    }

    private static Task retrieveTask(String commandDetails, TaskList taskList) throws MortalReminderException {
        int index = Integer.parseInt(commandDetails) - 1;
        return taskList.getTask(index);
    }
}
