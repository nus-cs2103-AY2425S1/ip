package tudee;

import tudee.storage.Storage;
import tudee.ui.Ui;
import tudee.parser.Parser;
import tudee.task.TaskList;
import tudee.command.Command;

public class Tudee {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Tudee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (tudee.TudeeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (tudee.TudeeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Tudee("./data/tudee.txt").run();
    }
}
