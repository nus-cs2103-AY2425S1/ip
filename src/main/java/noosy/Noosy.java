package noosy;

import noosy.parser.Parser;
import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.commands.Command;
import noosy.exception.NoosyException;
import noosy.task.TaskList;

import java.io.IOException;

public class Noosy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Noosy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NoosyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoosyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Noosy("data/noosy.txt").run();
    }
}