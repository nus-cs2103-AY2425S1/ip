package swbot;

import java.util.ArrayList;

import swbot.command.InputHandler;
import swbot.command.Storage;
import swbot.tasks.Task;

/**
 * The R2D2 class represents a chatbot that processes user commands and maintains a list of tasks.
 * It utilizes the Storage class for loading and saving tasks and the InputHandler class for processing commands.
 */
public class R2D2 {
    private static final String FILE_PATH = "R2D2.txt";
    private Storage storage;
    private InputHandler c3po;

    /**
     * Initializes a new R2D2 chatbot instance.
     * Creates a Storage object for handling task persistence and
     * loads any existing tasks from the specified file path.
     * Also initializes an InputHandler object for managing user input commands.
     */
    public R2D2() {
        this.storage = new Storage(FILE_PATH);
        storage.checkAndCreateFile();
        ArrayList<Task> database = storage.loadTasks();
        this.c3po = new InputHandler(database, storage);
    }

    /**
     * Processes the given user input and returns an appropriate response.
     *
     * @param input the user input to be processed by the chatbot
     * @return the chatbot's response to the input
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response = input.equals("bye") ? "Bye. Hope to see you again soon! *bzzzt*\n"
                    : c3po.overallHandler(input);
        } catch (BuzzException e) {
            response = " " + e.getMessage() + "\n";
        }
        return response;
    }
}
