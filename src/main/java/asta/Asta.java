package asta;

import java.util.List;

import asta.command.Command;
import asta.command.Parser;
import asta.task.Task;
import asta.task.TaskList;
import asta.ui.Main;
import asta.ui.Ui;
import javafx.application.Application;

/**
 * The Asta class encapsulates the following
 */
public class Asta {
    private static final String FILE_PATH = "./data/asta.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs an instance of the Asta bot. Initializes the Ui, Storage, and TaskList components. Loads the tasks
     * from the storage file and handles any errors that may occur during the loading process. If the storage file does
     * not exist or an error occurs, an empty TaskList is created.
     */
    public Asta() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (AstaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Runs the main logic of the application. Reads user input and processes commands.
     */
    public String getResponse(String fullCommand) {
        StringBuilder response = new StringBuilder();
        Command command = Parser.parse(fullCommand);
        try {
            switch (command) {
            case BYE:
                handleByeCommand(response);
                break;
            case LIST:
                handleListCommand(response);
                break;
            case MARK:
                handleMarkCommand(fullCommand, response);
                break;
            case UNMARK:
                handleUnmarkCommand(fullCommand, response);
                break;
            case TODO:
                handleTodoCommand(fullCommand, response);
                break;
            case DEADLINE:
                handleDeadlineCommand(fullCommand, response);
                break;
            case EVENT:
                handleEventCommand(fullCommand, response);
                break;
            case DELETE:
                handleDeleteCommand(fullCommand, response);
                break;
            case FIND:
                handleFindCommand(fullCommand, response);
                break;
            case RECURRING_DEADLINE:
                handleRecurringDeadlineCommand(fullCommand, response);
                break;
            default:
                AstaException.handleInvalidCommand();
                break;
            }
        } catch (AstaException e) {
            return ui.showError(e.getMessage());
        }
        return response.toString();
    }

    private void handleByeCommand(StringBuilder response) {
        response.append("Bye. Hope to see you again soon!");
    }

    private void handleListCommand(StringBuilder response) throws AstaException {
        response.append(tasks.listTasks());
    }

    private void handleMarkCommand(String fullCommand, StringBuilder response) throws AstaException {
        int markTaskNumber = tasks.getTaskNumber(fullCommand, "mark");
        tasks.markTask(markTaskNumber, true);
        response.append("Nice! I've marked this task as done:\n");
        response.append(tasks.getTask(markTaskNumber)).append("\n");
        storage.save(tasks.getTasks());
    }

    private void handleUnmarkCommand(String fullCommand, StringBuilder response) throws AstaException {
        int unmarkTaskNumber = tasks.getTaskNumber(fullCommand, "unmark");
        tasks.markTask(unmarkTaskNumber, false);
        response.append("OK, I've marked this task as not done yet:\n");
        response.append(tasks.getTask(unmarkTaskNumber)).append("\n");
        storage.save(tasks.getTasks());
    }

    private void handleTodoCommand(String fullCommand, StringBuilder response) throws AstaException {
        String todoDescription = fullCommand.substring(5).trim();
        tasks.addTodoTask(todoDescription);
        response.append("Got it. I've added this task:\n");
        response.append("[T][ ] ").append(todoDescription).append("\n");
        storage.save(tasks.getTasks());
    }

    private void handleDeadlineCommand(String fullCommand, StringBuilder response) throws AstaException {
        String[] deadlineParts = fullCommand.substring(9).split(" /by ");
        tasks.addDeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());
        response.append("Got it. I've added this task:\n");
        response.append(tasks.getTask(tasks.getSize() - 1)).append("\n");
        storage.save(tasks.getTasks());
    }

    private void handleEventCommand(String fullCommand, StringBuilder response) throws AstaException {
        String[] eventParts = fullCommand.substring(6).split(" /from | /to ");
        tasks.addEventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
        response.append("Got it. I've added this task:\n");
        response.append(tasks.getTask(tasks.getSize() - 1)).append("\n");
        storage.save(tasks.getTasks());
    }

    private void handleDeleteCommand(String fullCommand, StringBuilder response) throws AstaException {
        int deleteTaskNumber = tasks.getTaskNumber(fullCommand, "delete");
        Task removedTask = tasks.deleteTask(deleteTaskNumber);
        response.append("Noted. I've removed this task:\n");
        response.append(removedTask).append("\n");
        storage.save(tasks.getTasks());
    }

    private void handleFindCommand(String fullCommand, StringBuilder response) throws AstaException {
        String keyword = fullCommand.substring(5).trim();
        List<Task> matchingTasks = tasks.findTasks(keyword);
        response.append("Here are the matching tasks in your list:\n");
        matchingTasks.forEach(
                task -> response.append(matchingTasks.indexOf(task) + 1).append(". ").append(task).append("\n"));
    }

    private void handleRecurringDeadlineCommand(String fullCommand, StringBuilder response) throws AstaException {
        try {
            // Split the full command into parts by the "/by " and "/"
            String[] parts = fullCommand.substring(16).split(" /by ");

            if (parts.length < 2) {
                throw new AstaException("Invalid format. Please provide both a deadline and a recurrence.");
            }

            String description = parts[0].trim(); // Task description
            String[] dateAndRecurrence = parts[1].split(" /"); // Split deadline and recurrence

            if (dateAndRecurrence.length < 2) {
                throw new AstaException("Invalid format. Please provide both deadline and recurrence.");
            }

            String byDateTimeStr = dateAndRecurrence[0].trim(); // Deadline date/time string
            String recurrenceType = dateAndRecurrence[1].trim(); // Recurrence (e.g., "weekly")

            // Parse recurrence interval (daily, weekly, monthly)
            int recurrenceInterval = Parser.parseRecurrenceInterval(recurrenceType);

            // Add the recurring task to the task list
            tasks.addRecurringDeadlineTask(description, byDateTimeStr, recurrenceInterval);

            response.append("Got it. I've added this recurring deadline task:\n");
            response.append(tasks.getTask(tasks.getSize() - 1)).append("\n");
            storage.save(tasks.getTasks());
        } catch (AstaException e) {
            throw new AstaException("Error processing recurring deadline: " + e.getMessage());
        }
    }
}
