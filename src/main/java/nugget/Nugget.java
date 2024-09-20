package nugget;

import nugget.command.Command;
import nugget.exception.NuggetException;
import nugget.gui.ChatUIController;

public class Nugget {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private ChatUIController gui;

    public Nugget(String filePath, ChatUIController gui) {
        this.gui = gui;
        ui = new Ui(gui);
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);
    }

    public void start() {
        ui.printIntro();
    }

    public void handleInput(String input) {
        try {
            Command c = parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (NuggetException e) {
            ui.showError(e.getMessage());
        }
    }
}
