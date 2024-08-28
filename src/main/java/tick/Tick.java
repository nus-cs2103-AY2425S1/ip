package tick;

import java.io.IOException;

import tick.commands.Command;
import tick.exceptions.TickException;
import tick.parser.Parser;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

public class Tick {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Tick(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
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
            } catch (TickException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Tick("data/tasks.txt").run();
    }
}