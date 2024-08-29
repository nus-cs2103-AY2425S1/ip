package hoshi;

import hoshi.task.*;
import hoshi.ui.Ui;
import hoshi.utils.Parser;
import hoshi.utils.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Hoshi class contains the main flow of Hoshi and declares relevant classes for functionality
 *
 */
public class Hoshi {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes ui, storage, taskList and parser and loads tasks from text file
     *
     * @param filePath the file path of the Hoshi txt file where tasks are stored
     */
    public Hoshi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser= new Parser();

        try {
            storage.load(taskList);
        } catch (FileNotFoundException e) {
            ui.displayLoadingError(e.getMessage());
        }
    }

    /**
     * Runs Hoshi bot
     *
     * <p> Displays introduction ui, calls parser to parse user input and saves tasks at end of program</p>
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

    public static void main(String[] args) {
        new Hoshi("data/Hoshi.txt").run();

    }


}
