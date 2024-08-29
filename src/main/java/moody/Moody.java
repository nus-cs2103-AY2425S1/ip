package moody;

import moody.commands.Command;
import moody.exceptions.InvalidCommandException;
import moody.exceptions.TaskInputException;
import moody.exceptions.TaskOutOfBoundsException;
import moody.parser.Parser;
import moody.storage.Storage;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Moody {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Moody(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList loadedTasks = null;
        try {
            loadedTasks = new TaskList(storage.load());
            ui.showLine();
            ui.showLoadingSuccess();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        }

        this.tasks = loadedTasks != null ? loadedTasks : new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (InvalidCommandException | IOException
                     | TaskInputException | TaskOutOfBoundsException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Moody("./data/moody.txt").run();
    }
}
