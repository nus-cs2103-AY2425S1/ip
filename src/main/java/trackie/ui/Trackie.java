package trackie.ui;

import trackie.commands.Command;
import trackie.parsing.Parser;
import trackie.storage.Storage;
import trackie.storage.TaskList;

public class Trackie {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Trackie(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    public void run() {
        ui.greet();
        storage.load(tasks);
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            Command c = Parser.parseCommand(userInput);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Trackie("./src/data.txt").run();
    }
}
