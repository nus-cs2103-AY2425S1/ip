package park;

import park.commands.Command;
import park.exceptions.ParkException;
import park.parser.Parser;
import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

public class Park {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Park(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (ParkException e) {
            ui.showToUser(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ParkException e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Park("./data/Park.txt").run();
    }
}
