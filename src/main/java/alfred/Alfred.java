package alfred;

import java.io.IOException;
import java.util.List;

import alfred.exception.AlfredException;
import alfred.factory.TaskFactory;
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
        loadTasksFromStorage();
    }

    /**
     * Loads tasks from storage and initializes the task list.
     * Handles potential errors during loading by catching and delegating
     * the handling of IOExceptions and AlfredExceptions.
     */
    private void loadTasksFromStorage() {
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            handleIoExceptionDuringLoading(e);
        } catch (AlfredException e) {
            handleCorruptedSave(e);
        }
        assert tasks != null : "Task list should not be null after initialization";
    }

    /**
     * Handles an IOException that occurs during the loading of tasks from storage.
     * Sets the loadingError message and initializes an empty task list.
     *
     * @param e The IOException that occurred during task loading.
     */
    private void handleIoExceptionDuringLoading(IOException e) {
        loadingError = AlfredResponse.showLoadingError(e);
        this.tasks = new TaskList();
    }

    /**
     * Handles an AlfredException that occurs due to a corrupted task save file.
     * Sets the loadingError message, clears the storage, and initializes an empty task list.
     *
     * @param e The AlfredException that indicates the save file is corrupted.
     */
    private void handleCorruptedSave(AlfredException e) {
        loadingError = AlfredResponse.showCorruptedSaveError(e);
        storage.clearStorage();
        tasks = new TaskList();
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
        return processCommand(input, command);
    }

    /**
     * Processes the command extracted from the user's input string.
     * This method determines the correct operation to perform (list, mark, unmark, delete, find, create new task).
     *
     * @param input The user input string.
     * @param command The extracted command from the user input.
     * @return A string response generated based on the command provided.
     */
    private String processCommand(String input, String command) {
        switch (command) {
        case "list":
            return getTasks();
        case "mark":
            return markTask(input);
        case "unmark":
            return unmarkTask(input);
        case "delete":
            return deleteTask(input);
        case "find":
            return findTask(input);
        case "bye":
            return farewell();
        case "tag":
            return tagTask(input);
        case "untag":
            return untagTask(input);
        default:
            return createNewTask(input);
        }
    }

    /**
     * Handles the tag command by validating the input and performing the tag operation.
     * If validation fails, it returns an error message; otherwise, it proceeds with tagging.
     *
     * @param input The user input containing the task number and the tag.
     * @return A response indicating whether the task was successfully tagged or an error message if validation failed.
     */
    private String tagTask(String input) {
        String validationResult = Parser.validateCommand(input, "tag", tasks.getTasksCount());
        if (!validationResult.isEmpty()) {
            return validationResult;
        }
        return performTaggingTask(input);
    }

    /**
     * Handles the untag command by validating the input and performing the untag operation.
     * If validation fails, it returns an error message; otherwise, it proceeds with untagging.
     * If tag not present, task is untouched.
     *
     * @param input The user input containing the task number and the tag to be removed.
     * @return A response indicating whether the task was successfully untagged.
     */
    private String untagTask(String input) {
        String validationResult = Parser.validateCommand(input, "untag", tasks.getTasksCount());
        if (!validationResult.isEmpty()) {
            return validationResult;
        }
        return performUntaggingTask(input);
    }

    /**
     * Performs the tagging operation after input validation.
     * Extracts the task number and tag from the input and adds the tag to the specified task.
     *
     * @param input The user input containing the task number and tag.
     * @return A response confirming that the task was successfully tagged.
     */
    private String performTaggingTask(String input) {
        int taskNumber = Parser.getTaskNumberFromInput(input);
        String tag = Parser.getTagFromInput(input);
        Task taggedTask = tasks.tagTask(taskNumber, tag);

        String saveError = saveTasks();
        if (!saveError.isEmpty()) {
            return saveError;
        }

        return AlfredResponse.showTaskTagged(taggedTask);
    }

    /**
     * Performs the untagging operation after input validation.
     * Extracts the task number and tag from the input and removes the tag from the specified task.
     *
     * @param input The user input containing the task number and tag to be removed.
     * @return A response confirming that the task was successfully untagged.
     */
    private String performUntaggingTask(String input) {
        int taskNumber = Parser.getTaskNumberFromInput(input);
        String tag = Parser.getTagFromInput(input);
        Task taggedTask = tasks.untagTask(taskNumber, tag);

        String saveError = saveTasks();
        if (!saveError.isEmpty()) {
            return saveError;
        }

        return AlfredResponse.showTaskUntagged(taggedTask);
    }

    /**
     * Marks a task as done based on the user input.
     * Validates the input and returns a response indicating whether the task was successfully marked.
     *
     * @param input The user input string.
     * @return A string containing the success message or validation error.
     */
    public String markTask(String input) {
        String validationResult = Parser.validateCommand(input, "mark", tasks.getTasksCount());
        if (!validationResult.isEmpty()) {
            return validationResult;
        }
        return performMarkTask(input);
    }

    /**
     * Performs the logic to mark a task as done.
     * Retrieves the task number from the input and marks the task.
     *
     * @param input The user input string.
     * @return A string confirming the task was marked.
     */
    private String performMarkTask(String input) {
        int taskNumber = Parser.getTaskNumberFromInput(input);
        assert taskNumber > 0 && taskNumber <= tasks.getTasksCount() : "Invalid task number: " + taskNumber;
        Task markedTask = tasks.markTask(taskNumber);

        String saveError = saveTasks();
        if (!saveError.isEmpty()) {
            return saveError;
        }

        return AlfredResponse.showTaskMarked(markedTask);
    }

    /**
     * Unmarks a task (marks it as not done) based on the user input.
     * Validates the input and returns a response indicating whether the task was successfully unmarked.
     *
     * @param input The user input string.
     * @return A string containing the success message or validation error.
     */
    public String unmarkTask(String input) {
        String validationResult = Parser.validateCommand(input, "unmark", tasks.getTasksCount());
        if (!validationResult.isEmpty()) {
            return validationResult;
        }
        return performUnmarkTask(input);
    }

    /**
     * Performs the logic to unmark a task (i.e., mark it as not done).
     * Retrieves the task number from the input and unmarks the task.
     *
     * @param input The user input string.
     * @return A string confirming the task was unmarked.
     */
    private String performUnmarkTask(String input) {
        int taskNumber = Parser.getTaskNumberFromInput(input);
        assert taskNumber > 0 && taskNumber <= tasks.getTasksCount() : "Invalid task number: " + taskNumber;
        Task markedTask = tasks.unmarkTask(taskNumber);

        String saveError = saveTasks();
        if (!saveError.isEmpty()) {
            return saveError;
        }

        return AlfredResponse.showTaskUnmarked(markedTask);
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
        assert !tasks.getTasks().isEmpty() : "Task list should not be empty if tasks are not empty";
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
        if (!validationResult.isEmpty()) {
            return validationResult;
        }
        return performDeleteTask(input);
    }

    /**
     * Performs the logic to delete a task.
     * Retrieves the task number from the input and deletes the task.
     *
     * @param input The user input string.
     * @return A string confirming the task was deleted.
     */
    private String performDeleteTask(String input) {
        int taskNumber = Parser.getTaskNumberFromInput(input);
        Task deletedTask = tasks.deleteTask(taskNumber);

        String saveError = saveTasks();
        if (!saveError.isEmpty()) {
            return saveError;
        }

        return AlfredResponse.showTaskDeleted(deletedTask, tasks.getTasksCount());
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
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null or empty";
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
        if (!TaskFactory.isCreateTaskCommand(input)) {
            return AlfredResponse.showInvalidCommand();
        }
        return handleTaskCreation(input);
    }

    /**
     * Handles the creation of a new task based on the user input.
     * Initializes the task, adds it to the task list, and returns the appropriate response.
     * Catches exceptions related to task creation and provides error messages.
     *
     * @param input The user input string.
     * @return A string containing the result of task creation, or an error message if an exception occurs.
     */
    private String handleTaskCreation(String input) {
        try {
            Task task = TaskFactory.initialise(input);
            tasks.addTask(task);

            String saveError = saveTasks();
            if (!saveError.isEmpty()) {
                return saveError;
            }

            return AlfredResponse.showAddedTaskMessage(task, tasks.getTasksCount());
        } catch (AlfredException e) {
            return AlfredResponse.showAlfredError(e);
        } catch (Exception e) {
            return AlfredResponse.showUnexpectedError(e);
        }
    }

    /**
     * Says farewell to the user
     *
     * @return A farewell message
     */
    public String farewell() {
        return AlfredResponse.farewell();
    }

    /**
     * Saves task to storage.
     * If saving tasks fails, an error message is returned
     *
     * @return An empty string if the tasks are successfully saved, or an error message if an IOException occurs.
     */
    public String saveTasks() {
        try {
            storage.saveTasks(tasks.getTasks());
            return "";
        } catch (IOException e) {
            return AlfredResponse.showSavingError(e);
        }
    }

    public String getLoadingError() {
        return loadingError;
    }
}
