package lutchat;

import java.util.ArrayList;

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It parses the user input and triggers the appropriate actions in the TaskList.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param userInput The user's input as a string.
     * @param ui        The Ui object for interacting with the user interface.
     * @param taskList  The TaskList object that contains the current list of tasks.
     * @param storage   The Storage object responsible for saving and loading tasks.
     * @return A boolean indicating whether the application should continue running.
     */
    public static boolean parse(String userInput, Ui ui, TaskList taskList, Storage storage) {
        String[] userInputArr = userInput.trim().split(" ");
        String keyword = userInputArr[0];

        switch (keyword) {
            case "bye":
                return false;

            case "list":
                handleListCommand(taskList, ui);
                break;

            case "mark":
                handleTaskCommand(userInputArr, taskList, ui, true);
                break;

            case "unmark":
                handleTaskCommand(userInputArr, taskList, ui, false);
                break;

            case "delete":
                handleTaskDeletion(userInputArr, taskList, ui);
                break;

            case "todo":
                handleTodoTask(userInput.substring(4).trim(), taskList, ui);
                break;

            case "deadline":
                handleDeadlineTask(userInput.substring(8).trim(), taskList, ui);
                break;

            case "event":
                handleEventTask(userInput.substring(5).trim(), taskList, ui);
                break;

            case "find":
                handleFindTask(userInputArr, userInput, taskList, ui);
                break;

            default:
                ui.showError("I don't know what that means... :(");
        }
        return true;
    }

    private static void handleListCommand(TaskList taskList, Ui ui) {
        ui.showTaskList(taskList.getTasks());
    }

    private static void handleTaskCommand(String[] userInputArr, TaskList taskList, Ui ui, boolean isMarking) {
        int taskIndex = validateAndGetTaskIndex(userInputArr, taskList, ui);
        if (taskIndex == -1) {
            return; // Early return if index is invalid
        }

        assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Invalid task index during marking/unmarking.";
        if (isMarking) {
            taskList.markTaskAsDone(taskIndex, ui);
        } else {
            taskList.markTaskAsUndone(taskIndex, ui);
        }
    }

    private static void handleTaskDeletion(String[] userInputArr, TaskList taskList, Ui ui) {
        int taskIndex = validateAndGetTaskIndex(userInputArr, taskList, ui);
        if (taskIndex == -1) {
            return; // Early return if index is invalid
        }

        assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Invalid task index during deletion.";
        taskList.deleteTask(taskIndex, ui);
    }

    private static void handleTodoTask(String todoDesc, TaskList taskList, Ui ui) {
        if (todoDesc.isEmpty()) {
            ui.showError("Todo description is missing.");
            return; // Early return for invalid todo description
        }

        Task todo = new Todo(todoDesc);
        assert todo != null : "Todo creation failed.";
        taskList.addTask(todo, ui);
    }

    private static void handleDeadlineTask(String input, TaskList taskList, Ui ui) {
        String[] deadlineParts = input.split("/by");

        if (deadlineParts.length < 2 || deadlineParts[0].isEmpty()) {
            ui.showError("Deadline description or 'by' input is missing.");
            return; // Early return for invalid deadline
        }

        String deadlineDesc = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        try {
            Task deadline = new Deadline(deadlineDesc, by);
            assert deadline != null : "Deadline creation failed.";
            taskList.addTask(deadline, ui);
        } catch (IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleEventTask(String input, TaskList taskList, Ui ui) {
        String[] eventParts = input.split("/from");

        if (eventParts.length < 2 || eventParts[0].isEmpty()) {
            ui.showError("Event description, 'from', or 'to' input is missing.");
            return; // Early return for invalid event description
        }

        String eventDesc = eventParts[0].trim();
        String[] fromTo = eventParts[1].split("/to");

        if (fromTo.length < 2 || fromTo[0].isEmpty()) {
            ui.showError("Event 'from' or 'to' input is missing.");
            return; // Early return for invalid 'from' or 'to' input
        }

        String from = fromTo[0].trim();
        String to = fromTo[1].trim();

        try {
            Task event = new Event(eventDesc, from, to);
            assert event != null : "Event creation failed.";
            taskList.addTask(event, ui);
        } catch (IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void handleFindTask(String[] userInputArr, String userInput, TaskList taskList, Ui ui) {
        if (userInputArr.length < 2) {
            ui.showError("Please indicate a keyword to search.");
            return; // Early return for invalid search input
        }

        String searchWord = userInput.substring(5).trim();
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : taskList.getTasks()) {
            if (task.contains(searchWord)) {
                filteredTasks.add(task);
            }
        }

        ui.showTaskList(filteredTasks);
    }

    /**
     * Validates the user input and retrieves the task index.
     *
     * @param userInputArr The split user input array.
     * @param taskList     The current task list.
     * @param ui           The UI for displaying error messages.
     * @return The task index if valid, otherwise -1.
     */
    private static int validateAndGetTaskIndex(String[] userInputArr, TaskList taskList, Ui ui) {
        if (userInputArr.length < 2) {
            ui.showError("Please indicate the task number.");
            return -1; // Early return for missing task number
        }

        try {
            int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
            assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Task index out of bounds.";
            return taskIndex;
        } catch (NumberFormatException e) {
            ui.showError("Invalid task number. Please enter a valid number.");
        }

        return -1; // Invalid input fallback
    }
}
