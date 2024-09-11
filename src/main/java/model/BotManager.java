package model;

import java.io.FileNotFoundException;

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
    private final TaskList taskList;

    /**
     * Constructor for a BotManager.
     * Initialises the helper classes needed.
     */
    public BotManager() {
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Attempts to load task data from the data file.
     * @return <code>Response</code> describing the status of the action
     */
    public Response loadData() {
        try {
            return storage.loadTaskList(taskList);
        } catch (FileNotFoundException e) {
            return storage.initFile();
        }
    }

    /**
     * Gets the response for BotManager for a given user input.
     *
     * @param input Input from the user.
     * @return <code>Response</code> describing the status of the action.
     */
    public Response[] getResponse(String input) {
        try {
            Action action = parser.parseInput(input.strip());
            Response actionResponse = new Response(action.execute(taskList), action.isExit());
            Response saveResponse = storage.saveTaskList(taskList);
            return new Response[] {actionResponse, saveResponse};
        } catch (BotException e) {
            return new Response[]{new Response(e.getMessage(), false)};
        }
    }
}
