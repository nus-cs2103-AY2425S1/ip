package skywalker;

import skywalker.command.Command;
import skywalker.parser.Parser;
import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

import java.io.IOException;

public class Skywalker {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Skywalker(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
    }



    public static void main(String[] args) {
        new Skywalker("./data/tasks.txt").run();
    }
}
