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
                response.append("Bye. Hope to see you again soon!");
                break;
            case LIST:
                response.append(tasks.listTasks());
                break;
            case MARK:
                int markTaskNumber = tasks.getTaskNumber(fullCommand, "mark");
                tasks.markTask(markTaskNumber, true);
                response.append("Nice! I've marked this task as done:\n");
                response.append(tasks.getTask(markTaskNumber)).append("\n");
                storage.save(tasks.getTasks());
                break;
            case UNMARK:
                int unmarkTaskNumber = tasks.getTaskNumber(fullCommand, "unmark");
                tasks.markTask(unmarkTaskNumber, false);
                response.append("OK, I've marked this task as not done yet:\n");
                response.append(tasks.getTask(unmarkTaskNumber)).append("\n");
                storage.save(tasks.getTasks());
                break;
            case TODO:
                String todoDescription = fullCommand.substring(5).trim();
                tasks.addTodoTask(todoDescription);
                response.append("Got it. I've added this task:\n");
                response.append("[T][ ] ").append(todoDescription).append("\n");
                storage.save(tasks.getTasks());
                break;
            case DEADLINE:
                String[] deadlineParts = fullCommand.substring(9).split(" /by ");
                tasks.addDeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());
                response.append("Got it. I've added this task:\n");
                response.append(tasks.getTask(tasks.getSize() - 1)).append("\n");
                storage.save(tasks.getTasks());
                break;
            case EVENT:
                String[] eventParts = fullCommand.substring(6).split(" /from | /to ");
                tasks.addEventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                response.append("Got it. I've added this task:\n");
                response.append(tasks.getTask(tasks.getSize() - 1)).append("\n");
                storage.save(tasks.getTasks());
                break;
            case DELETE:
                int deleteTaskNumber = tasks.getTaskNumber(fullCommand, "delete");
                Task removedTask = tasks.deleteTask(deleteTaskNumber);
                response.append("Noted. I've removed this task:\n");
                response.append(removedTask).append("\n");
                storage.save(tasks.getTasks());
                break;
            case FIND:
                String keyword = fullCommand.substring(5).trim();
                List<Task> matchingTasks = tasks.findTasks(keyword);
                response.append("Here are the matching tasks in your list:\n");
                matchingTasks.forEach(task -> response.append(matchingTasks.indexOf(task) + 1).append(". ").append(task)
                        .append("\n"));
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
}
