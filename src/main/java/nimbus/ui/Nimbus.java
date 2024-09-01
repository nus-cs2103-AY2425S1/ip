package nimbus.ui;

import nimbus.exception.*;

import java.io.*;

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
        new Nimbus("src/main/data/nimbus.txt").run();
    }
}