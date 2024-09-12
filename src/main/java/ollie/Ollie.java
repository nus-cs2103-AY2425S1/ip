package ollie;

import ollie.command.Command;
import ollie.exception.OllieException;

/**
 * Main Class. Ollie is the name of our chatbot. It contains the method
 * main and is executable.
 */
public class Ollie {
    // Constants
    private static final String filepath = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private History history;
    private String initialMessage;

    public Ollie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        history = new History();
        try {
            tasks = new TaskList(storage.load());
            initialMessage = ui.getGreetingMessage();
        } catch (OllieException e) {
            tasks = new TaskList();
            initialMessage = e.getMessage();
        }
    }

    String getInitialMessage() {
        return initialMessage;
    }

    /**
     * Starts the chatbot.
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage, history);
        } catch (OllieException e) {
            return new Response(e.getMessage(), false);
        }
    }
}
