package star.main;

import star.command.*;
import star.exception.StarException;

/**
 * Star is a chatbot that allows users to manage their tasks.
 */
public class Star {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructs a new instance of the chatbot Star.
     * @param filePath is the path of the file used to store tasks from the taskList.
     */
    public Star(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StarException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets Star's 'response' to the user input.
     * @param input the user input in String form.
     * @return the String form of Star's 'response' to the user input.
     */
    public String getResponse(String input) {
        if (isExit) {
            return "";
        }
        try {
            Command c = Parser.parse(input);
            System.out.println("hello");
            String output = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return output;
        } catch (StarException e) {
            return e.getMessage();
        }
    }
}
