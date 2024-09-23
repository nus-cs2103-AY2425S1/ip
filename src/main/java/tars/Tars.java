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

    private final Storage storage = new Storage();
    private final Response response = new Response();
    private final TaskList tasks = new TaskList(storage.updateTasks());

    public String displayIntro() {
        return response.displayIntro();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The input of the user to the chatbot,
     * @return A {@link String} representing the response of the chatbot
     */
    public String getResponse(String input) {

        return switch (input) {
            case "bye" -> {
                storage.saveTasks(tasks);
                yield response.displayOutro();
            }
            case "list" -> tasks.toString();
            default -> response.generateResponse(input, tasks);
        };


    }
}
