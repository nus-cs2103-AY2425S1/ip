package applemazer;

import java.util.ArrayList;
import java.util.Scanner;

import commands.Command;
import tasks.Task;

/**
 * Main class that runs the Applemazer chatbot.
 */
public class Applemazer {
    protected static TaskList tasks;
    protected static Scanner sc = new java.util.Scanner(System.in); // For text-based UI.
    private static Storage storage;
    private final Parser parser; // For text-based UI.
    private final Ui ui;
    private boolean isProcessing = true;

    /**
     * Constructor for the Applemazer chatbot.
     * @param filePath The file to load a task list from.
     */
    public Applemazer(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ArrayList<Task> savedList = storage.load();
        assert savedList != null : "The task list is null.";
        tasks = new TaskList(savedList);
        parser = new Parser(sc);
    }

    /**
     * Main processing loop for the Applemazer chatbot.
     * <p>
     * Executes commands based on user input.
     */
    private void process() {
        System.out.println(ui.greeting());
        boolean isProcessing = true;
        while (isProcessing) {
            String command = sc.next();
            try {
                Command c = parser.parse(command);
                System.out.print(c.execute(tasks, storage, ui));
                isProcessing = c.continueProcessing();
            } catch (Exception e) {
                System.err.println(e.getMessage()); // Catches parsing errors.
            }
        }
        sc.close();
        System.out.println(ui.farewell());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Scanner sc = new Scanner(input + " ");
        Parser parser = new Parser(sc);
        String command = sc.next();
        try {
            Command c = parser.parse(command);
            isProcessing = c.continueProcessing();
            return c.execute(tasks, storage, ui);
        } catch (Exception e) {
            return e.getMessage() + "\n"; // Catches parsing errors.
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    public boolean shouldExit() {
        return !this.isProcessing;
    }

    /**
     * Entry point of the Applemazer chatbot program.
     * @param args Unused CLI arguments.
     */
    public static void main(String[] args) {
        Applemazer applemazer = new Applemazer("./data/Applemazer.ser");
        applemazer.process();
    }
}
