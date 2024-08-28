public class EchoBot {
    private static final TaskList tasks = new TaskList();

    public static void main(String[] args) {
        Storage.loadTasksFromFile(tasks); // Load tasks when starting
        Parser parser = new Parser(tasks); // Initialize parser
        Ui ui = new Ui();

        ui.start();
        while (true) {
            String userInput = ui.nextInput();
            Command command = parser.parse(userInput);
            command.run();
            if (command.isExit()) {
                break;
            }
        }
        ui.exit();
    }
}
