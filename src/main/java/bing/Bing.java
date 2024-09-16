package bing;

import java.io.IOException;
import bing.storage.Storage;
import bing.command.*;
import bing.task.TaskList;
import bing.ui.Ui;
import bing.parser.Parser;

public class Bing {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Bing(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Failed to load tasks.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        new Bing("data/tasks.txt").run();
    }
}
