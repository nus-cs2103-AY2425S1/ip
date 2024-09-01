package drbrown;

import drbrown.command.Command;
import drbrown.utils.*;

public class DrBrown {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public DrBrown(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DrBrownException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DrBrownException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new DrBrown("data/DrBrown.txt").run();
    }

}
