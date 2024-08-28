package BotimusPrime;

import BotimusPrime.Parser.Parser;
import BotimusPrime.Storage.Storage;
import BotimusPrime.Tasks.TaskList;
import BotimusPrime.UI.Ui;

public class BotimusPrime {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public BotimusPrime(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        taskList = storage.loadFromDisk();
        parser = new Parser(taskList,ui, storage);
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = parser.parse(fullCommand);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
        ui.bye();
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new BotimusPrime("todolist.txt").run();
    }
}