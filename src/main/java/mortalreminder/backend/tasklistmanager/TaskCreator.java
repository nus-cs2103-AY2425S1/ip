package mortalreminder.backend.tasklistmanager;

import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.Deadline;
import mortalreminder.tasks.Event;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.ToDo;

/**
 * Handles creation of different types of tasks simultaneously.
 */
public class TaskCreator {

    /**
     * Creates a new task based on the command type and adds it to the task list.
     * <p>
     * This method interprets the command details to create the appropriate type of task
     * (ToDo, Deadline, or Event) and adds it to the task list. It handles potential errors
     * such as an incorrect number of details or an invalid date format. This method is also
     * optimised using ChatGPT.
     *
     * @param commandDetails the details of the command, typically the task description.
     * @param taskList       the {@link TaskList} to add the new task to.
     * @param commandType    the {@link CommandType} indicating the type of task to create.
     * @return returns a confirmation message of type of task created or the reason why the task could not be created.
     * @throws MortalReminderException if there is an error in the initialisation in the new {@link Task} to be created.
     */
    public static String createTask(String commandDetails, TaskList taskList, CommandType commandType)
            throws MortalReminderException {
        Task newTask;
        if (commandType == CommandType.TODO) {
            newTask = new ToDo(commandDetails);
        } else if (commandType == CommandType.DEADLINE) {
            newTask = new Deadline(commandDetails);
        } else if (commandType == CommandType.EVENT) {
            newTask = new Event(commandDetails);
        } else {
            throw new MortalReminderException("This statement should be unreachable, code has an error!");
        }
        return taskList.addTask(newTask);
    }
}
