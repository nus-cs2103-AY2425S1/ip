import friday.command.Command;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Parser;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

public class Friday {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Friday() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTasks());
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
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FridayException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Friday().run();
    }
}
