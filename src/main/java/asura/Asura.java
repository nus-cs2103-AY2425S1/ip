package asura;

import asura.commands.Command;
import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.parser.Parser;
import asura.storage.Storage;
import asura.ui.Ui;

public class Asura {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Asura(String filepath) {
        ui = new Ui();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.showIntroduction();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AsuraException e) {
                ui.showError(e.getMessage());
            }

        }

        ui.showGoodbye();
    }


    public static void main(String[] args) {
        new Asura("data/asura.txt").run();
    }
}
