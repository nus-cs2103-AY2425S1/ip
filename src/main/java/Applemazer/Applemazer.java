package applemazer;

import commands.Command;
import java.util.Scanner;


/**
 * Main class that runs the Applemazer chatbot.
 */
public class Applemazer {
    private final Ui ui;
    private static Storage storage;
    public static TaskList tasks;
    public static Scanner sc = new Scanner(System.in);

    /**
     * Constructor for the Applemazer chatbot.
     * @param filePath The file to load a task list from.
     */
    public Applemazer(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Main processing loop for the Applemazer chatbot.
     * <p>
     * Executes commands based on user input.
     */
    private void process() {
        ui.greeting();
        boolean processing = true;
        while (processing) {
            if (!sc.hasNext()) { break; } // For automated testing of text UIs.
            String command = sc.next();
            try {
                Command c = Parser.parse(command);
                c.execute(tasks, storage);
                processing = c.continueProcessing();
            } catch (Exception e) {
                System.err.println(e.getMessage() + "\n"); // Catches parsing errors.
            }
        }
        sc.close();
        ui.farewell();
    }

    /**
     * Entry point of the Applemazer chatbot program.
     * @param args Unused CLI arguments.
     */
    public static void main(String[] args) {
        new Applemazer("./data/Applemazer.ser").process();
    }
}
