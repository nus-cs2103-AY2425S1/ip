package nimbus;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.Storage;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

import java.io.IOException;

public class Nimbus {
    private Storage storage;
    private Ui ui;
    private TaskList taskList = new TaskList();
    public Nimbus(String filepath) {
        this.storage = new Storage(filepath);
        storage.createFile();
        storage.loadFile(taskList);
        this.ui = new Ui(taskList);
    }

    private void run() throws WrongDateTimeFormatException, IOException {
        Ui.showWelcome();
        ui.run();
    }

    public static void main(String[] args)
            throws IOException, WrongDateTimeFormatException {
        new Nimbus("nimbus.txt").run();
    }
}