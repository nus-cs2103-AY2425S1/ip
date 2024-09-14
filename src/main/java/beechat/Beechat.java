package beechat;

import java.lang.String;
import java.io.IOException;

public class Beechat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Beechat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isLeave = false;
        while (!isLeave) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isLeave = c.isLeave();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Beechat("data/beechat.txt").run();
    }
}