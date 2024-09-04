import command.Command;
import data.InsufficientInfoException;
import data.NoSuchCommandException;
import parser.Parser;
import storage.FileCorruptedException;
import storage.Storage;
import storage.StorageOperationException;
import task.TaskList;
import task.TaskType;
import ui.Ui;
import utils.exceptions.IllegalValueException;

import java.util.Scanner;

/**
 * Entry point of Mylo.
 * Initializes the application and starts the interaction with the user.
 */
public class Mylo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the required objects and loads up the data from the storage file.
     */
    public Mylo(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = storage.load();
        } catch (FileCorruptedException | StorageOperationException e) {
            System.out.println(e.getMessage() + "\nPlease try again.");
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.separator();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (NoSuchCommandException | StorageOperationException | InsufficientInfoException |
                     IllegalValueException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.separator();
            }
        }
    }

    public static void main(String[] args) {
        new Mylo("data/tasks.txt").run();
    }
}
