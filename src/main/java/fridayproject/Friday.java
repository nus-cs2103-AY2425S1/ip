package fridayproject;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the main class of the program.
 */
public class Friday {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Friday(String filePathString) {
        ui = new Ui();
        storage = new Storage(filePathString);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.displayLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, storage, ui);
    }

    /*
     * Runs the main program loop.
     */
    public void run() {
        ui.displayWelcome();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            parser.parseCommand(input);
        }
    }

    /*
     * Main method to run the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Friday("data/tasks.txt").run();
    }
}

// done with the guidance of ChatGPT

  