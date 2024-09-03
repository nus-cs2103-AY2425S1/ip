package taskalyn;

public class Taskalyn {
    public static void main(String[] args) {

        // Initialising
        Ui ui = new Ui();
        Database database = new Database();
        TaskManager taskManager = new TaskManager(database, ui);
        Parser parser = new Parser(ui, taskManager);
        ui.printWelcome();

        while (true) {
            boolean continueRunning = parser.parse(taskManager);
            // String lastCommand = ui.getLastCommand();
            // if (lastCommand.equals("bye")) {
            //     break;
            // }
            if (!continueRunning) {
                break;
            }
        }
    }
}
