package nimbus;

import nimbus.exception.*;
import nimbus.ui.Storage;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

import java.io.*;

/**
 * Main class that starts the project
 */

public class Nimbus {
    private Storage storage;
    private Ui ui;
    private TaskList taskList = new TaskList();

    /**
     * Creates a nimbus object and runs the chatbot
     *
     * @param filepath where the file is supposed to be at
     */

    public Nimbus(String filepath) {
        this.storage = new Storage(filepath);
        storage.createFile();
        storage.loadFile(taskList);
        this.ui = new Ui(taskList);
    }

    /**
     * Starts the program by loading the UI
     *
     * @throws WrongDateTimeFormatException if wrong format for date and time
     * @throws IOException if file is not found
     */

    private void run() throws WrongDateTimeFormatException, IOException {
        Ui.showWelcome();
        ui.run();
    }

    /**
     * Starts the project
     *
     * @param args arguments that is not necessary for this project
     * @throws IOException if file is not found
     * @throws WrongDateTimeFormatException if wrong format for date and time
     */

    public static void main(String[] args)
            throws IOException, WrongDateTimeFormatException {
        new Nimbus("nimbus.txt").run();
    }
}