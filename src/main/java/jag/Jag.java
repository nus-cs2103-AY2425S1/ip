package jag;

import java.io.FileNotFoundException;

/**
 * Represents an instance of the chatbot named Jag
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
        ui = new UiCLI();
        storage = new Storage(path);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Jag() {
        ui = new UiGUI();
        storage = new Storage("./data/jag.txt");
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response from Jag
     */
    public String getResponse(String input) throws AExceptions {
        try {
            ui.setCommand(input);
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getResponse();
        } catch (AExceptions e) {
            return e.getErrorMessage();
        }
    }

    /**
     * Method to run the application on CLI
     * Starts the chatbot with a welcome message
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
