package juno.command;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Deadline;
import juno.task.Task;

/**
 * A class to add a new Deadline task to the task list.
 * Handles the creation and addition of a Deadline task based on user input.
 */
public class AddDeadlineCommand extends AddCommand {

    private final String TASK_TYPE = "deadline";

    /**
     * Constructs an AddDeadlineCommand instance that takes in a specified user input, TaskManager instance,
     * and FileManager instance.
     * Initialises an AddDeadlineCommand instance with the provided parameters below.
     *
     * @param userInput The input provided by the user to specify the task to add.
     * @param taskManager The TaskManager instance to handle all task specific operations.
     * @param fileManager The FileManager to handle file operations related to tasks.
     */
    public AddDeadlineCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        super(userInput, taskManager, fileManager);
    }

    /**
     * Executes the command to add a new Deadline task.
     * Based on the user input, extract the task description and end time, checks for duplicates, and then
     * create a new Deadline task, adds it to the task list, and updates the file.
     *
     * <p>Handle cases such as missing task description and duplicate tasks by throwing TaskManagerException.
     * Writes the updated task list to the file after addition.</p>
     *
     * @throws TaskManagerException If an error occurs during task addition, such as missing task description.
     */
    @Override
    public String runCommand() throws TaskManagerException {
        String taskInfo;
        assert this.userInput != null : "User input in AddDeadlineCommand() cannot be null!";
        assert this.tasks != null : "Task array should not be null!";
        try {
            taskInfo = userInput.split("\\s+", 3)[2];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? "
                    + "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} "
                    + "/ {Input task description here}\" to add a task)",
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
        }

        String taskDescription;
        Task t;
        try {
            String[] taskInfoArray = taskInfo.split("/", 2);
            taskDescription = taskInfoArray[0];

            // Check if the task already exists
            if (super.taskManager.isDuplicateTask(taskDescription)) {
                throw new TaskManagerException("This task is already in your list! "
                        + "Maybe you can try renaming it and input again?",
                        TaskManagerException.ErrorType.DUPLICATE_TASK);
            }
            t = new Deadline(taskDescription, taskInfoArray[1], this.TASK_TYPE);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? "
                    + "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} "
                    + "/ {Input task description here}\" to add a task)",
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
        }
        this.tasks.add(t);
        this.fileManager.writeTasksToFile(this.tasks);
        StringBuilder outString = new StringBuilder("\uD83C\uDF89 Got it! I've added: \""
                + taskDescription
                + "\" to your list!");
        outString.append("\n").append("\uD83C\uDFAF You now have ")
                 .append(this.tasks.size())
                 .append(" tasks in the list. Keep going!");
        return outString.toString();
    }
}
