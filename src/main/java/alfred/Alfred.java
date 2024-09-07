package alfred;

import java.io.IOException;
import java.util.List;

import alfred.exception.AlfredException;
import alfred.parser.Parser;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.AlfredResponse;

/**
 * Represents the main class that manages the operations and interactions within
 * the Alfred task management application.
 */
public class Alfred {
    private TaskList tasks;
    private Storage storage;
    private String loadingError;

    /**
     * Initializes a new instance of <code>Alfred</code> with the specified file path
     * for saving and loading tasks. Attempts to load tasks from storage and handles
     * errors related to loading and file corruption.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public Alfred(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            loadingError = AlfredResponse.showLoadingError(e);
            this.tasks = new TaskList();
        } catch (AlfredException e) {
            loadingError = AlfredResponse.showCorruptedSaveError(e);
            storage.clearStorage();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message based on the input command.
     * It handles different operations such as listing tasks, marking, unmarking, deleting,
     * finding, and creating tasks.
     *
     * @param input The user input as a string.
     * @return The response string that contains the result of the operation.
     */
    public String getResponse(String input) {
        String command = Parser.getCommand(input);
        StringBuilder response = new StringBuilder();

        switch (command) {
        case "list":
            response.append(getTasks());
            break;
        case "mark":
            response.append(markTask(input, command));
            break;
        case "unmark":
            response.append(unmarkTask(input, command));
            break;
        case "delete":
            response.append(deleteTask(input));
            break;
        case "find":
            response.append(findTask(input));
            break;
        case "bye":
            response.append(farewell());
            break;
        default:
            response.append(createNewTask(input));
            break;
        }
        return response.toString();
    }

    /**
     * Marks a task as done based on the user input.
     * Validates the input and returns a response indicating whether the task was successfully marked.
     *
     * @param input The user input string.
     * @param command The command to mark the task.
     * @return A string containing the success message or validation error.
     */
    public String markTask(String input, String command) {
        String validationResult = Parser.validateCommand(input, command, tasks.getTasksCount());
        if (validationResult.isEmpty()) {
            int taskNumber = Parser.getTaskNumberFromInput(input);
            Task markedTask = tasks.markTask(taskNumber);
            return AlfredResponse.showTaskMarked(markedTask);
        }
        return validationResult;
    }

    /**
     * Unmarks a task (marks it as not done) based on the user input.
     * Validates the input and returns a response indicating whether the task was successfully unmarked.
     *
     * @param input The user input string.
     * @param command The command to unmark the task.
     * @return A string containing the success message or validation error.
     */
    public String unmarkTask(String input, String command) {
        String validationResult = Parser.validateCommand(input, command, tasks.getTasksCount());
        if (validationResult.isEmpty()) {
            int taskNumber = Parser.getTaskNumberFromInput(input);
            Task markedTask = tasks.unmarkTask(taskNumber);
            return AlfredResponse.showTaskUnmarked(markedTask);
        }
        return validationResult;
    }

    /**
     * Retrieves and returns the current list of tasks in a formatted string.
     * Returns message saying there are no task if tasks are empty.
     *
     * @return A string containing the formatted task list. Returns message if tasks empty.
     */
    public String getTasks() {
        if (tasks.isEmpty()) {
            return AlfredResponse.getNoTasksMessage();
        }
        return AlfredResponse.getTaskList(tasks.getTasks());
    }

    /**
     * Deletes a task based on the user input.
     * Validates the input and returns a response indicating whether the task was successfully deleted.
     *
     * @param input The user input string.
     * @return A string containing the success message or validation error.
     */
    public String deleteTask(String input) {
        String validationResult = Parser.validateCommand(input, "delete", tasks.getTasksCount());

        if (validationResult.isEmpty()) {
            int taskNumber = Parser.getTaskNumberFromInput(input);
            Task deletedTask = tasks.deleteTask(taskNumber);
            return AlfredResponse.showTaskDeleted(deletedTask, tasks.getTasksCount());
        } else {
            return validationResult;
        }
    }

    /**
     * Finds tasks based on the keyword provided in the user input.
     * Returns a list of tasks that match the keyword.
     *
     * @param input The user input string containing the keyword.
     * @return A string containing the tasks that match the search keyword.
     */
    public String findTask(String input) {
        String keyword = Parser.getKeyword(input);
        List<Task> foundTasks = tasks.findTasks(keyword);
        return AlfredResponse.getFoundTasks(foundTasks);
    }

    /**
     * Creates a new task based on the user input.
     * Handles exceptions during task creation and returns the appropriate response.
     *
     * @param input The user input string that contains the task creation command.
     * @return A string containing the success message for task creation or any error messages.
     */
    public String createNewTask(String input) {
        if (!Task.isCreateTaskCommand(input)) {
            return AlfredResponse.showInvalidCommand(); // Early return for invalid command
        }

        try {
            Task task = Task.initialise(input); // Initialize task
            tasks.addTask(task); // Add task to the list
            return AlfredResponse.showAddedTaskMessage(task, tasks.getTasksCount()); // Success message
        } catch (AlfredException e) {
            return AlfredResponse.showAlfredError(e); // Handle Alfred-specific exception
        } catch (Exception e) {
            return AlfredResponse.showUnexpectedError(e); // Handle any other exceptions
        }
    }

    /**
     * Says farewell to the user and saves task to storage
     * If saving tasks fails, an error message is returned
     *
     * @return A farewell message if the tasks are successfully saved, or an error message if an IOException occurs.
     */
    public String farewell() {
        try {
            storage.saveTasks(tasks.getTasks());
            return AlfredResponse.farewell();
        } catch (IOException e) {
            return AlfredResponse.showSavingError(e);
        }
    }

    public String getLoadingError() {
        return loadingError;
    }
}
