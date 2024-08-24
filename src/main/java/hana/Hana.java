package hana;

import hana.command.Command;
import hana.parser.Parser;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

import java.util.ArrayList;

public class Hana {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Hana() {
        ui = new Ui();
        tasks = new TaskList(new ArrayList<>());
        storage = new Storage(tasks.getTasks());
        try {
            storage.load();
        } catch (HanaException e) {
            ui.printError(e.getMessage());
        }
    }

    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.save();
                isExit = c.isExit();
            } catch (HanaException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Hana().run();
    }
}