package spongebob;

import spongebob.command.Command;
import spongebob.exception.SpongebobException;
import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.ui.Ui;

/**
 * main class to run the bot
 */
public class Spongebob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Takes in a filepath for loading the storage, creates the UI components and tasklist
     */
    public Spongebob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage);
        } catch (SpongebobException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        Command c = parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

    /**
     * starts the bot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser();
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Spongebob("data/spongebob.txt").run();
    }
}
