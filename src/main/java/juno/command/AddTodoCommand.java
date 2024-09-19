package juno.command;

import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;
import juno.task.Todo;

/**
 * A class to add a new Todo task to the task list.
 * Handles the creation and addition of a Todo task based on user input.
 */
public class AddTodoCommand extends AddCommand {

    private static final String TASK_TYPE = "todo";
    private static final String SPLIT_TASK_DELIMITER = "\\s+";
    private static final String DUPLICATE_TASK_ERROR = "This task is already in your list! "
            + "Maybe you can try renaming it and input again?";
    private static final String INVALID_ADD_TASK_ERROR = "\uD83D\uDE15 Hmm, something went wrong. Did you add task "
            + "correctly? (\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} "
            + "/ {Input task description here}\" to add a task)";

    /**
     * Constructs a AddTodoCommand that takes in a specified user input, TaskManager instance, and FileManager instance.
     * Initialises an AddTodoCommand instance with the provided parameters below
     *
     * @param userInput The input provided by the user to specify the task to add.
     * @param taskManager The TaskManager instance to handle all task specific operations.
     * @param fileManager The FileManager to handle file operations related to tasks.
     */
    public AddTodoCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        super(userInput, taskManager, fileManager);
    }

    /**
     * Executes the command to add a new Todo task.
     * Based on the user input, extract the task description, checks for duplicates, and then
     * create a new Todo task, adds it to the task list, and updates the file.
     *
     * <p>Handle cases such as missing task description and duplicate tasks by throwing TaskManagerException.
     * Writes the updated task list to the file after addition.</p>
     *
     * @throws TaskManagerException If an error occurs during task addition, such as missing task description.
     */
    @Override
    public String runCommand() throws TaskManagerException {
        String taskInfo;
        assert this.userInput != null : "User input in AddTodoCommand() cannot be null!";
        assert this.tasks != null : "Task array should not be null!";
        try {
            taskInfo = this.userInput.split(SPLIT_TASK_DELIMITER, 3)[2];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException(INVALID_ADD_TASK_ERROR,
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
        }

        // Check if the task already exists
        if (super.taskManager.isDuplicateTask(taskInfo)) {
            throw new TaskManagerException(DUPLICATE_TASK_ERROR, TaskManagerException.ErrorType.DUPLICATE_TASK);
        }
        Task t = new Todo(taskInfo, AddTodoCommand.TASK_TYPE);
        this.tasks.add(t);
        this.fileManager.writeTasksToFile(this.tasks);
        return super.buildSuccessMessage(taskInfo);
    }
}
