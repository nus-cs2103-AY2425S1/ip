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
        tasks = new TaskList();
        storage = new Storage(filepath, tasks);

    }

    public void run() {
        ui.greet();
        storage.load();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            Command c = Parser.parseCommand(userInput);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
