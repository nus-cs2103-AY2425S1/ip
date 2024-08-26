package killua;

import killua.command.Command;
import killua.util.*;

import java.io.IOException;
public class Killua {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Killua(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandStr = ui.readCommand();
                Command command = Parser.parseCommand(commandStr);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (KilluaException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Killua("./data/tasks.txt").run();
    }
}
