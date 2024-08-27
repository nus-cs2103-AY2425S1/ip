package max.main;

import max.exception.MaxException;
import max.task.TaskList;

import java.util.Scanner;

/**
 * The Max class is the entry point of the application. It initializes the necessary components
 * and handles the main logic of the application.
 */
public class Max {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/max.txt";


    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    /**
     * Constructor for the Max class. Initializes the UI, storage, and task list.
     */
    public Max() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadList());

    }

    /**
     * Runs the main loop of the application. It handles user input, processes commands
     * through the Parser, and outputs the results via the UI.
     */
    public void runMax() {
        Scanner scanner = new Scanner(System.in);
        ui.printHello();

        Parser parser = new Parser(tasks, ui, storage);
        try {
            parser.parseText(scanner);
        } catch (MaxException e) {
            ui.printMessage(e.getMessage());
        }

        ui.printBye();
    }


}
