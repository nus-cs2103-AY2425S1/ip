package mysutong;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MySutong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MySutong(String filePath) {
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
        while (true) {
            String fullCommand = ui.readCommand();
            new Parser().executeCommand(fullCommand, tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        new MySutong("data/tasks.txt").run();
    }
}
