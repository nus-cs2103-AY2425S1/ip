package prince;

import java.nio.file.Path;
import java.nio.file.Paths;

import prince.command.Command;
import prince.exceptions.PrinceException;
import prince.tasks.TaskList;
import prince.util.Parser;
import prince.util.Storage;
import prince.util.Ui;

/**
 * Prince is a chatbot that interacts with users.
 */
public class Prince {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for Prince.
     * @param filePath Path that represents a relative path to the storage file.
     */
    public Prince(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (PrinceException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Prince.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PrinceException e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Prince(Paths.get("data", "storage.txt")).run();
    }
}
