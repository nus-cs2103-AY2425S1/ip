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
        assert input.length() > 1 : "String length is nor more than 1";
        try {
            ui.setCommand(input);
            Command c = Parser.parse(input);
            assert (c instanceof Command) : "Object is not an instance of Command";
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
                assert (c instanceof Command) : "c is not an instance of Command";
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
