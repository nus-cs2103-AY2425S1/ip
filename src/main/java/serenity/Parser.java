package serenity;
import java.io.IOException;

/**
 * Represents a parser that parses user inputs and carries out the actions corresponding to the command.
 */

public class Parser {

    /**
     * Obtains command from user input and carries out the corresponding actions.
     *
     * @param input User's input.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Data storage.
     * @throws SerenityException If user input is invalid and task cannot be created.
     * @throws IOException If there is an issue writing to file that stores data.
     */
    public static void parse(String input, TaskList tasks, Ui ui, Storage storage)
            throws SerenityException, IOException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].strip();
        String message;
        Task t;

        switch (command) {
        case "bye":
            ui.showGoodbye();
            break;
        case "list":
            ui.showTaskList(tasks);
            break;
        case "todo", "deadline", "event":
            t = tasks.createTask(input);
            message = tasks.addTask(t);
            ui.showMessage(message);
            storage.saveTask(t);
            break;
        case "mark", "unmark":
            message = tasks.changeStatus(input);
            ui.showMessage(message);
            storage.writeToFile(tasks);
            break;
        case "delete":
            message = tasks.deleteTask(input);
            ui.showMessage(message);
            storage.writeToFile(tasks);
            break;
        default:
            ui.showMessage("Error: Invalid task.");
            break;
        }

    }

    /**
     * Checks if command is to exit chatbot.
     *
     * @param input User's input.
     * @return True if the command is bye.
     */

    public static boolean isExit (String input) {
        return input.startsWith("bye");
    }

}
