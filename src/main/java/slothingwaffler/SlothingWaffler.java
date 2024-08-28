package slothingwaffler;

import java.util.Scanner;

/**
 * Main class for running the Slothing Waffler application.
 * <p>
 * This class handles the application flow, including user input and command processing.
 * </p>
 */
public class SlothingWaffler {

    private static final Ui ui = new Ui();
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a SlothingWaffler instance with the specified file name for storage.
     *
     * @param fileName the name of the file to store and load tasks
     */
    public SlothingWaffler(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new TaskList(storage);
    }

    /**
     * Entry point for the application.
     * <p>
     * Creates an instance of SlothingWaffler and starts the application.
     * </p>
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new SlothingWaffler("data.txt").run();
    }

    private void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isTerminate = false;

        while (!isTerminate) {
            try {
                String input = scanner.nextLine();
                isTerminate = Parser.parse(input, tasks, ui, storage);
            } catch (SlothingWafflerException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("YUM. The Waffler is ready for your next command!");
            }
        }
        scanner.close();
    }

}
