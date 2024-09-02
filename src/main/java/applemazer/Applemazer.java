package applemazer;

import java.util.Scanner;

import commands.Command;

/**
 * Main class that runs the Applemazer chatbot.
 */
public class Applemazer {
    protected static TaskList tasks;
    protected static Scanner sc = new java.util.Scanner(System.in);
    private static Storage storage;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructor for the Applemazer chatbot.
     * @param filePath The file to load a task list from.
     */
    public Applemazer(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(sc);
    }

    /**
     * Main processing loop for the Applemazer chatbot.
     * <p>
     * Executes commands based on user input.
     */
    private void process() {
        ui.greeting();
        boolean isProcessing = true;
        while (isProcessing) {
            if (!sc.hasNext()) {
                break;
            } // For automated testing of text UIs.
            String command = sc.next();
            try {
                Command c = parser.parse(command);
                c.execute(tasks, storage);
                isProcessing = c.continueProcessing();
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
