package cypherchatbot;
import cypherchatbot.command.Command;
import cypherchatbot.util.CommandReader;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

import java.io.FileNotFoundException;

public class Cypher {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Cypher(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(filePath);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean shouldEnd = false;
        while (!shouldEnd) {
            try {
                String fullCommand = ui.readCommand();
                Command c = CommandReader.parse(fullCommand);
                c.execute(tasks,ui,storage);
                shouldEnd = c.isExit();
            } catch (CypherException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Cypher("./data/tasks.txt").run();
    }
}

