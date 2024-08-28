package bro;

import bro.command.Command;
import bro.parser.Parser;
import bro.storage.Storage;
import bro.task.TaskList;
import bro.ui.UI;

public class Bro {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Bro(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            tasks = storage.loadFromStorage();
        } catch (BroException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                UI.showLine();
                Command c = Parser.parse(fullCommand, this.tasks, this.storage);
                c.execute(this.ui);
                isExit = c.isExit();
            } catch (BroException e) {
                this.ui.showError(e.getMessage());
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Bro("data/tasks.txt").run();
    }
}
