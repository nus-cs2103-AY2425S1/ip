package lexi;

import lexi.command.Command;
import lexi.exception.LexiException;
import lexi.parser.Parser;
import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

public class Lexi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lexi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LexiException e) {
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
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LexiException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Lexi("data/tasks.txt").run();
    }
}
