import commands.Command;
import exception.TaskonException;
import storage.Storage;
import task.*;
import ui.Ui;
import parser.Parser;

public class Taskon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Taskon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TaskonException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TaskonException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     * The main method runs the Taskon application.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        new Taskon("./data/taskon.txt").run();
    }
}
