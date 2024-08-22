public class Fishman {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;

    public Fishman() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
    }

    public void start() {
        ui.displayLogo();
        ui.displayWelcome();
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.readCommands();
            Command command = Parser.parse(userInput);
            command.execute(tasks,ui);
            isExit = command.isExit();
        }
    }
    public static void main(String[] args) {
        new Fishman().start();
    }
}

