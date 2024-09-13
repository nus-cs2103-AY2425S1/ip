package buddy;

import command.Command;
import exceptions.BuddyException;
import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class Buddy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;

    public Buddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            assert storage.load() != null : "storage.load() should not be null";
            tasks = new TaskList(storage.load());
        } catch (BuddyException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String runStartupMsg() {
        return ui.displayWelcome();
    }
    public String runExitMsg() {
        return ui.displayGoodbye();
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BuddyException e) {
                ui.displayError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                this.isExit = true;
            }
            return command.execute(tasks, ui, storage);
        } catch (BuddyException e) {
            return ui.displayError(e.getMessage());
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    public static void main(String[] args) {
        new Buddy("data/buddy.txt").run();
    }
}