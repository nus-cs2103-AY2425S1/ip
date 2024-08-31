package muller;

import muller.ui.Ui;
import muller.storage.Storage;
import muller.parser.Parser;
import muller.task.TaskList;
import muller.command.Command;
import muller.command.MullerException;

public class Muller {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Muller(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MullerException e) {
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
                Parser p = new Parser();
                Command c = p.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MullerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Muller("data/muller.txt").run();
    }
}
