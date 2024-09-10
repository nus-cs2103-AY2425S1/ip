package tars;

import tars.storage.Storage;
import tars.tasks.TaskList;


/**
 * The main class for running the TARS application.
 *
 * <p>The {@code Tars} class handles the user interaction loop, allowing users to
 * interact with the task management system by entering commands. It provides functionality
 * to display introductory messages, process user input, execute commands, and save tasks
 * to storage.</p>
 */
public class Tars {

    Storage storage = new Storage();
    Response response = new Response();
    TaskList tasks = new TaskList(storage.updateTasks());

    public String intro() {
        return response.intro();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {

        return switch (input) {
            case "bye" -> {
                storage.saveTasks(tasks);
                yield response.outro();
            }
            case "list" -> tasks.toString();
            default -> response.generateResponse(input, tasks);
        };


    }

    /**
     * The main entry point for the TARS application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {


    }
}
