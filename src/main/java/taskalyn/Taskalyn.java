package taskalyn;

/**
 * Starts the Taskalyn application.
 */
public class Taskalyn {

    /**
     * Starts the Taskalyn application by initialising other classes.
     *
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {

        // Initialising
        Ui ui = new Ui();
        Database database = new Database();
        TaskManager taskManager = new TaskManager(database, ui);
        Parser parser = new Parser(ui, taskManager);
        ui.printWelcome();

        while (true) {
            boolean continueRunning = parser.parse(taskManager);
            if (!continueRunning) {
                break;
            }
        }
        ui.close();
    }
}
