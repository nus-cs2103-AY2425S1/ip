package orion.chatbot;

import orion.commands.Command;
import orion.exceptions.OrionException;
import orion.utils.Parser;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * The Orion class represents the main chatbot application.
 * It handles the initialization of the storage and task list,
 * and contains the main logic for running the chatbot.
 */
public class Orion {

    /**
     * The storage component responsible for loading and saving tasks.
     */
    private final Storage storage;

    /**
     * The task list that holds the tasks loaded from storage or created during runtime.
     */
    private TaskList tasks;

    /**
     * Constructs an Orion chatbot instance.
     * Initializes the storage, and task list.
     * Greets the user and loads tasks from storage.
     * If tasks cannot be loaded, initializes an empty task list.
     */
    public Orion() {
        storage = new Storage();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (OrionException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user input, executes the corresponding command, and returns the response.
     * This method parses the user input using the {@link Parser}, executes the
     * appropriate {@link Command}, and returns the result of the execution. In case
     * of any exceptions, it catches them and returns an appropriate error message.
     *
     * @param input The user input to be processed.
     * @return The response message after executing the user's command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (OrionException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unexpected error! " + e.getMessage();
        }
    }
}
