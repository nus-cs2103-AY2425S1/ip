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
                ui.showTaskList(taskList.getTasks());
                return true;

            case "mark":
                handleTaskMarking(userInputArr, taskList, ui, true);
                return true;

            case "unmark":
                handleTaskMarking(userInputArr, taskList, ui, false);
                return true;

            case "delete":
                handleTaskDeletion(userInputArr, taskList, ui);
                return true;

            case "todo":
                handleTodoTask(userInput.substring(4).trim(), taskList, ui);
                return true;

            case "deadline":
                handleDeadlineTask(userInput.substring(8).trim(), taskList, ui);
                return true;

            case "event":
                handleEventTask(userInput.substring(5).trim(), taskList, ui);
                return true;

            case "find":
                handleFindTask(userInputArr, userInput, taskList, ui);
                return true;

            default:
                ui.showError("I don't know what that means... :(");
                return true;
        }
    }

    /**
     * Handles marking or unmarking tasks.
     */
    private static void handleTaskMarking(String[] userInputArr, TaskList taskList, Ui ui, boolean isMarking) {
        if (userInputArr.length < 2) {
            ui.showError("Please indicate which task you would like to " + (isMarking ? "mark" : "unmark") + ".");
        } else {
            try {
                int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Invalid task index.";
                if (isMarking) {
                    taskList.markTaskAsDone(taskIndex, ui);
                } else {
                    taskList.markTaskAsUndone(taskIndex, ui);
                }
            } catch (NumberFormatException e) {
                ui.showError("Invalid task number. Please enter a valid number.");
            }
        }
    }

    /**
     * Handles task deletion.
     */
    private static void handleTaskDeletion(String[] userInputArr, TaskList taskList, Ui ui) {
        if (userInputArr.length < 2) {
            ui.showError("Please indicate which task you would like to delete.");
        } else {
            try {
                int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Invalid task index for deletion.";
                taskList.deleteTask(taskIndex, ui);
            } catch (NumberFormatException e) {
                ui.showError("Invalid task number. Please enter a valid number.");
            }
        }
    }

    /**
     * Handles adding a Todo task.
     */
    private static void handleTodoTask(String todoDesc, TaskList taskList, Ui ui) {
        if (todoDesc.isEmpty()) {
            ui.showError("Todo description is missing.");
        } else {
            Task todo = new Todo(todoDesc);
            assert todo != null : "Todo creation failed.";
            taskList.addTask(todo, ui);
        }
    }

    /**
     * Handles adding a Deadline task.
     */
    private static void handleDeadlineTask(String input, TaskList taskList, Ui ui) {
        String[] deadlineParts = input.split("/by");
        if (deadlineParts.length < 2 || deadlineParts[0].isEmpty()) {
            ui.showError("Deadline description or 'by' input is missing.");
        } else {
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
    }

    /**
     * Handles adding an Event task.
     */
    private static void handleEventTask(String input, TaskList taskList, Ui ui) {
        String[] eventParts = input.split("/from");
        if (eventParts.length < 2 || eventParts[0].isEmpty()) {
            ui.showError("Event description, 'from', or 'to' input is missing.");
        } else {
            String eventDesc = eventParts[0].trim();
            String[] fromTo = eventParts[1].split("/to");
            if (fromTo.length < 2 || fromTo[0].isEmpty()) {
                ui.showError("Event 'from' or 'to' input is missing.");
            } else {
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
        }
    }

    /**
     * Handles finding tasks by keyword.
     */
    private static void handleFindTask(String[] userInputArr, String userInput, TaskList taskList, Ui ui) {
        if (userInputArr.length < 2) {
            ui.showError("Please indicate a keyword to search.");
        } else {
            String searchWord = userInput.substring(5).trim();
            ArrayList<Task> filteredTasks = new ArrayList<>();
            for (Task task : taskList.getTasks()) {
                if (task.contains(searchWord)) {
                    filteredTasks.add(task);
                }
            }
            ui.showTaskList(filteredTasks);
        }
    }
}
