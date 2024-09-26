package jeriel;

import jeriel.command.*;
import jeriel.util.*;
import java.io.IOException;

public class Jeriel {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jeriel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns the chatbot's response.
     *
     * @param input The user's input
     * @return The chatbot's response after processing the input
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);  // Parse the user's input into a command
            return command.execute(tasks, ui, storage);  // Execute the command and return the result
        } catch (JerielException | IOException e) {
            return e.getMessage();  // Return error messages if something goes wrong
        }
    }
}
