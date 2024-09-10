package twilight;

/**
 * Acts as the main class for running the chatbot.
 */
public class Twilight {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates Twilight for a given storage filepath.
     *
     * @param filePath Path where list of tasks is stored.
     */
    public Twilight(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getStoredTasks());
    }

    /**
     * Returns the String told by Twilight according to the user input.
     *
     * @param input Command from user.
     * @return Response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (InvalidInputException e) {
            return e.toString();
        }
    }

    /**
     * Returns a message to welcome the user upon start up.
     */
    public static String greet() {
        return "Hello! I am Twilight your personal assistant\nWhat can I do for you?";
    }

    /**
     * Returns a message to indicate end of program.
     */
    public static String bidFarewell() {
        return "See you";
    }
}
