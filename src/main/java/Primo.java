import commands.Command;
import exception.PrimoException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class Primo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Primo(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load()); // throws PE
        } catch (PrimoException | IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand); // throws PE
                c.execute(tasks, ui, storage); // throws PE
                isExit = c.isExit();
            } catch (PrimoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Primo("data/tasks.txt").run();
    }
}