package botmanager;

import java.io.FileNotFoundException;
import java.io.IOException;

import action.Action;
import exception.BotException;
import task.TaskList;

/**
 * Entry point for the BotManager Chatbot.
 *
 * @author dwsc37
 */
public class BotManager {
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList = new TaskList();

    /**
     * Constructor for a BotManager.
     * Initialises the helper classes needed.
     */
    public BotManager() {
        parser = new Parser();
        storage = new Storage();
        try {
            storage.loadTaskList(taskList);
        } catch (FileNotFoundException e) {
            storage.initFile();
        }
    }

    /**
     * Gets the response for BotManager for a given user input.
     *
     * @param input Input from the user.
     * @return Response from BotManager.
     */
    public String getResponse(String input) {
        // parse and execute command
        String response = "";
        try {
            Action action = parser.parseInput(input.strip());
            response += action.execute(taskList);
            storage.saveTaskList(taskList);
            return response;
        } catch (BotException e) {
            return e.getMessage();
        } catch (IOException e) {
            return response + "\n" + e.getMessage();
        }
    }

}
