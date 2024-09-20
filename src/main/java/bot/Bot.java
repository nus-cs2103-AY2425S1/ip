package bot;

import java.io.FileNotFoundException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import bot.action.Action;
import bot.constants.Message;
import bot.exceptions.BotException;
import bot.storage.Storage;
import bot.tasks.TaskList;

/**
 * Represents a bot that the user interacts with.
 */
public class Bot {
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    /**
     * Creates a new <code>Bot</code> object.
     */
    public Bot() {
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage();
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load task from disk" + e.getMessage());
        }
    }

    /**
     * Handles a given user input.
     *
     * @param input string keyed in by the user.
     * @return a string response message that is displayed via the GUI.
     */
    public String handleInput(String input) {
        try {
            Action action = parser.parseInput(input);
            String actionResponse = action.execute(tasks);
            String saveResponse = storage.saveTaskList(tasks);
            String response = actionResponse + "\n\n" + saveResponse;

            // If it is a terminal action, we close the program asynchronously after 3 seconds
            if (action.isTerminal()) {
                CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> System.exit(0));
            }

            return response;
        } catch (BotException e) {
            return Message.ERROR + "\n\n" + e.getMessage() + "\n\n" + Message.HELP;
        }
    }
}
