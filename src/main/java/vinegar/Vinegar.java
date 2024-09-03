package vinegar;

import vinegar.command.Command;
import vinegar.storage.Storage;
import vinegar.task.TaskList;
import vinegar.ui.Ui;

import java.io.IOException;

public class Vinegar {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Vinegar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (VinegarException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Vinegar("./data/vinegar.txt").run();
    }
}
