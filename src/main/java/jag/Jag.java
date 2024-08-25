package jag;

import java.io.FileNotFoundException;

public class Jag {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;



    public Jag(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AExceptions e) {
                ui.showError(e.getErrorMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Jag("./data/jag.txt").run();
    }
}