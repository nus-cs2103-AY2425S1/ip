package mylo;

import mylo.storage.FileCorruptedException;
import mylo.storage.Storage;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Gui;
import mylo.ui.Tui;
import mylo.ui.UiController;

/**
 * Entry point of Mylo.
 * Initializes the application and starts the interaction with the user.
 *
 * @author cweijin
 */
public class Mylo {
    private Storage storage;
    private TaskList taskList;
    private UiController controller;
    private Tui tui;
    private Gui gui;

    /**
     * Initializes the required objects and loads up the data from the storage file.
     */
    public Mylo(String filePath) {
        try {
            storage = new Storage();
            taskList = storage.load();
            tui = new Tui();
            gui = Gui.getInstance();
        } catch (FileCorruptedException | StorageOperationException e) {
            System.out.println(e.getMessage() + "\nPlease try again.");
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        // Set the model and start the TUI after JavaFX initialization is complete
        controller = new UiController(gui, tui, taskList);
        Gui.getInstance().setController(controller);
    }

    public static void main(String... args) {
        new Mylo("data/tasks.txt").run();
    }
}
