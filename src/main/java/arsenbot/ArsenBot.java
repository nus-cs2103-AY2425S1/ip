package arsenbot;

import arsenbot.command.Command;
import arsenbot.command.Parser;
import arsenbot.storage.Storage;
import arsenbot.task.Task;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArsenBot {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    public ArsenBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        List<Task> loadedTasks = new ArrayList<>();
        try {
            loadedTasks = storage.load();
        } catch (IOException e) {
            ui.showError("Error: Unable to load tasks from file.");
        }
        tasks = new TaskList(loadedTasks);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (TaskManagerException | IOException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new ArsenBot("./data/history.txt").run();
    }
}
