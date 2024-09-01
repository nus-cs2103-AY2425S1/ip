package mummy.ui;

import java.io.IOException;

import mummy.command.Command;
import mummy.task.TaskList;
import mummy.utility.Parser;
import mummy.utility.Storage;


public class Mummy {
    private static final String LOGO = " __  __\n"
            + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n"
            + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
            + "| |  | | |_| | | | | | | | | | | | |_| |\n"
            + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
            + "                                  |___/ \n";

    private static final String ioPath = "./data/mummy.txt";

    private Storage storage;

    private TaskList taskList;

    private Ui ui;

    private Mummy(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (IOException e) {
            this.taskList = new TaskList();
        }

        this.ui = new Ui(LOGO);
    }

    private void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Command.of(Parser.parse(fullCommand));
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (MummyException exception) {
                ui.showError(exception.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Mummy(ioPath).run();
    }
}
