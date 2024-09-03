package tayoo;

import tayoo.command.Command;
import tayoo.exception.TayooException;

public class Tayoo {
    private static final String NAME = "tayoo";
    private Ui ui;
    private Storage storage;
    public Tasklist tasks;

    public Tayoo() {
        try {
            this.storage = new Storage();
            this.ui = new Ui(NAME);
            if (storage.createTxt()) {
                ui.printText("Creating a new tasklist.txt for you.");
            }
            this.tasks = new Tasklist(storage.readTxt());
        } catch (TayooException e) {
            ui.printError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Tayoo tayoo = new Tayoo();
        tayoo.run();
        System.exit(0);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TayooException e) {
                ui.printError(e.getMessage());
            }
         }
    }


}
