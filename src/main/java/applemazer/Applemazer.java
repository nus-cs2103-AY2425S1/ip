package applemazer;

import java.util.Scanner;

import commands.Command;

/**
 * Main class that runs the Applemazer chatbot.
 */
public class Applemazer {
    protected static TaskList tasks;
    private static Storage storage;
    private final Ui ui;

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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Scanner sc = new Scanner(input + " ");
        Parser parser = new Parser(sc);
        String command = sc.next();
        System.out.println(command);
        try {
            Command c = parser.parse(command);
            return c.execute(tasks, storage, ui);
        } catch (Exception e) {
            return e.getMessage() + "\n"; // Catches parsing errors.
        }
    }
}
