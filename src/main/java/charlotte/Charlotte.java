package charlotte;

import charlotte.command.Command;
import charlotte.exception.CharlotteException;
import charlotte.parser.Parser;
import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

public class Charlotte {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Charlotte(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (CharlotteException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CharlotteException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Charlotte("data/charlotte.txt").run();
    }

}