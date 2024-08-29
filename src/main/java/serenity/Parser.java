package serenity;
import java.io.IOException;

public class Parser {

    public static void parse(String input, TaskList tasks, Ui ui, Storage storage) throws SerenityException, IOException {
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
        case "find":
            message = tasks.findTask(input);
            ui.showMessage(message);
            break;
        default:
            ui.showMessage("Error: Invalid task.");
            break;
        }

    }

    public static boolean isExit (String input) {
        return input.startsWith("bye");
    }


}
