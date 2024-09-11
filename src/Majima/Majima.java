package Majima;

import Majima.command.Command;
import Majima.ui.Ui;
import Majima.storage.Storage;
import Majima.task.TaskList;

public class Majima {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Majima(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MajimaException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.userGreet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //placeholder for ui.linegap
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MajimaException e) {
                ui.showError(e.getMessage());
            } finally {
                //placeholder
            }
        }
    }

    public static void main(String[] args) {
        new Majima("data/Majima.txt").run();
    }
}
