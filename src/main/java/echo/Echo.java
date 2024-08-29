package echo;

import echo.backend.Storage;
import echo.task.TaskList;

public class Echo {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    public Echo(String filePath) {
        this.storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (EchoException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        ui = new Ui(taskList);
    }

    public void run() {
        ui.acceptInput();
        storage.saveToFile();
    }
    public static void main(String[] args) {
        new Echo("src/main/data/savedTasks.txt").run();
    }
}
