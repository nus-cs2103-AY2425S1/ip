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
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate which task you would like to mark.");
                } else {
                    try {
                        int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                        assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Invalid task index for marking.";
                        taskList.markTaskAsDone(taskIndex, ui);
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number. Please enter a valid number.");
                    }
                }
                return true;

            case "unmark":
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate which task you would like to unmark.");
                } else {
                    try {
                        int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                        assert taskIndex >= 0 && taskIndex < taskList.getTasks().size() : "Invalid task index for unmarking.";
                        taskList.markTaskAsUndone(taskIndex, ui);
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number. Please enter a valid number.");
                    }
                }
                return true;

            case "delete":
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate which task you would like to delete.");
                } else {
                    try {
                        int taskIndex = Integer.parseInt(userInputArr[1]) - 1;
                        taskList.deleteTask(taskIndex, ui);
                        assert taskList.getTasks().size() == (Integer.valueOf(userInputArr[1]) - 1) : "Task list size doesn't match expected size after deletion.";
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number. Please enter a valid number.");
                    }
                }
                return true;

            case "todo":
                String todoDesc = userInput.substring(4).trim();
                if (todoDesc.isEmpty()) {
                    ui.showError("Todo 'description' is missing...");
                } else {
                    Task todo = new Todo(todoDesc);
                    assert todo != null  : "Todo creation failed.";
                    taskList.addTask(todo, ui);
                }
                return true;

            case "deadline":
                String[] deadlineParts = userInput.substring(8).trim().split("/by");
                if (deadlineParts.length < 2 || deadlineParts[0].isEmpty()) {
                    ui.showError("Deadline 'description' or 'by' input(s) is/are missing...");
                } else {
                    String deadlineDesc = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    try {
                        Task deadline = new Deadline(deadlineDesc, by);
                        assert deadline != null  : "Deadline creation failed.";
                        taskList.addTask(deadline, ui);
                    } catch (IllegalArgumentException e) {
                        ui.showError(e.getMessage());
                    }
                }
                return true;

            case "event":
                String[] eventParts = userInput.substring(5).trim().split("/from");
                if (eventParts.length < 2 || eventParts[0].isEmpty()) {
                    ui.showError("Event 'description', 'from', or 'to' input(s) is/are missing...");
                } else {
                    String eventDesc = eventParts[0].trim();
                    String[] fromTo = eventParts[1].split("/to");
                    if (fromTo.length < 2 || fromTo[0].isEmpty()) {
                        ui.showError("Event 'from' or 'to' input(s) is/are missing...");
                    } else {
                        String from = fromTo[0].trim();
                        String to = fromTo[1].trim();
                        try {
                            Task event = new Event(eventDesc, from, to);
                            assert event != null  : "Event creation failed.";
                            taskList.addTask(event, ui);
                        } catch (IllegalArgumentException e) {
                            ui.showError(e.getMessage());
                        }
                    }
                }
                return true;

            case "find":
                if (userInputArr.length < 2) {
                    ui.showError("Please indicate 1 keyword...");
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
                return true;

            default:
                ui.showError("I don't know what that means... :(");
                return true;
        }
    }
}
