package hoshi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Parser;
import hoshi.utils.Storage;


/**
 * Main class for Hoshi
 */
public class Hoshi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Hoshi method
     */
    public Hoshi(String filePath) {

        ui = new Ui();

        storage = new Storage(filePath);

        taskList = new TaskList();

        parser = new Parser();

        try {
            storage.load(taskList);
        } catch (FileNotFoundException e) {
            ui.displayLoadingError(e.getMessage());
        }
    }

    /**
     * Run method - main flow of Hoshi
     */
    public void run() {

        ui.displayWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            ui.displayPrompt();
            input = scanner.nextLine();
            parser.parseCommand(input, scanner, taskList, ui);

        } while (!input.equalsIgnoreCase("bye"));

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.displaySavingError(e.getMessage());
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        new Hoshi("data/Hoshi.txt").run();

    }


}
