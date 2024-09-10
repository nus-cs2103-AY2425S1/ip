package elysia;

import elysia.command.Command;
import elysia.parser.Parser;
import elysia.exception.ElysiaException;
import elysia.storage.Storage;
import elysia.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Entry point of the (Elysia) chatbot application.
 * Initializes the application.
 */
public class Elysia {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;

    public Elysia() throws IOException {
        storage = new Storage(tasks);
        storage.load();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command command = parser.parseCommand(input);
            return command.execute(tasks);
        } catch (ElysiaException | IOException e) {
            return e.getMessage();
        }
    }

}