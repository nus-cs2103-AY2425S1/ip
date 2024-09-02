package zero;

import zero.exception.ZeroException;
import zero.task.TaskList;
import zero.ui.Ui;
import zero.util.Parser;
import zero.util.Storage;

public class Zero {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Zero(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ZeroException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        while(!ui.isDone()) {
            try {
                String command = ui.readCommand();
                Parser.parseCommand(command, tasks, ui);
                storage.save(tasks);
            }catch (ZeroException e) {
                ui.showError(e.getMessage());
            }

        }

    }

    public static void main(String[] args) {
        new Zero("data/zero.txt").run();
    }
}