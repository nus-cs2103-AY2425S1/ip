package elon;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main entry point for the Elon application.
 * Initializes and runs the application, handling user input and interactions.
 */
public class Elon {
    Ui ui = new Ui();

    private Storage storage;

    /**
     * Constructs an Elon object with the specified Storage.
     *
     * @param storage the Storage object used for loading and saving tasks
     */
    public Elon(Storage storage) {
        this.storage = storage;
    }


    /**
     * Starts the application by greeting the user, loading the task list,
     * and processing user input commands through the Parser.
     *
     * @throws ElonException if an error occurs during command processing
     */
    private void Run() throws ElonException {
        ui.greet();
        TaskList list = new TaskList(storage.loadFile());
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        Parser parser = new Parser(this.ui, list, scanner, inputArr, this.storage);
        parser.Parse();
    }

    /**
     * The main method to run the Elon application.
     * Initializes the Storage and Elon objects and starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("./Data.txt");
            Elon elon = new Elon(storage);
            elon.Run();
        } catch (ElonException | IOException e) {
            System.out.println(e);
        }
    }
}
