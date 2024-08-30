import jdk.jfr.Event;

public class Taskalyn {
    public static void main(String[] args) {

        // Initialising
        Ui ui = new Ui();
        Database database = new Database();
        TaskManager taskManager = new TaskManager(database, ui);
        Parser parser = new Parser(ui, taskManager);
        ui.printWelcome();

        while (true) {
            parser.parse(taskManager);
        }
    }
}
