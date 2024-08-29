package sage;

import sage.Command.Command;
import sage.List.TaskList;

import java.io.IOException;

public class Sage {
    public static TaskList tasks;
    public static Ui ui;
    public static Storage storage;

    public static void main(String[] args) {
        new Sage("data/sage.txt").run();
    }

    public Sage(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | SageException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();

            } catch (SageException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
