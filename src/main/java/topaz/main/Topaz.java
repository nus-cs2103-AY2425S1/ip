package topaz.main;

import java.io.IOException;

import topaz.command.Command;
import topaz.exception.InvalidCommandException;
import topaz.ui.Ui;

public class Topaz {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public enum TaskType {
        E, // Event
        D, // Deadline
        T, // Todo
    }

    public Topaz(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showInitializeIOEException(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showException(e);
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Topaz("data/Topaz.txt").run();
    }


}
