package myapp.core;

import myapp.command.Parser;
import myapp.exception.RubyException;
import myapp.storage.Storage;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.ui.Ui;
import java.io.IOException;
import java.util.List;

public class Ruby {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    public Ruby(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            List<Task> tasks = storage.load();
            taskList = new TaskList(tasks);
        } catch (IOException | RubyException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                String response = parser.parse(command, taskList, ui, storage);
                ui.showResponse(response);
                isExit = parser.isExit(command);
            } catch (RubyException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Ruby("data/tasks.txt").run();
    }
}
