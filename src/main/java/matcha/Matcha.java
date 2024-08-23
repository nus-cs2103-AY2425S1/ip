package matcha;

import matcha.command.Command;

import matcha.exception.MatchaException;

import matcha.parser.Parser;

import matcha.storage.Storage;

import matcha.tasklist.TaskList;

import matcha.ui.Ui;

/**
 * Represents the main class of the Matcha program.
 */
public class Matcha {
    private static final String FILE_PATH = "./data/matcha.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Matcha class.
     *
     * @param filePath File path with saved task file.
     */
    public Matcha(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (MatchaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program to allow users to interact with Matcha.
     */
    public void run() {
        //greet users
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                //read user input
                String input = ui.readInput();
                ui.printDivider();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MatchaException e) {
                System.out.println(e);
            } finally {
                ui.printDivider();
            }
        }
        //once user has exited program, close scanner
        ui.closeScanner();
    }

    /**
     * Main method to start the Matcha program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Matcha(FILE_PATH).run();
    }
}