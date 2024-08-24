package Spongebob;

import Spongebob.command.Command;
import Spongebob.exception.SpongebobException;
import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;

/**
 * main class to run the bot
 */
public class Spongebob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Takes in a filepath for loading the storage, creates the UI components and tasklist
     */
    public Spongebob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage);
        } catch (SpongebobException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
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
