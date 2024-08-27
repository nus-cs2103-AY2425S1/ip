package jag;

import java.io.FileNotFoundException;

/**
 * Represents an instance of the chat bot named Jag
 */
public class Jag {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for the chatbot application Jag
     *
     * @param path Path of the file to read and write outputs
     */
    public Jag(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to run the application
     * Starts the chat bot with a welcome message
     * Continues until the "bye" command is given
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AExceptions e) {
                ui.showError(e.getErrorMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Jag("./data/jag.txt").run();
    }
}