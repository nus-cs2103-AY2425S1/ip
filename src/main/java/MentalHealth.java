import java.util.Scanner;

public class MentalHealth {

    private final static String separator = "------------------------------";
    private final static String greetingMessage = "Hello! I'm your friendly ChatBot assistant called MentalHealth :)\n" +
            "What can I do for you?";

    private final static String goodbyeMessage = "Bye. Hope to see you again soon! If you ever need help don't forget to reach out :)";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MentalHealth(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MentalHealthException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        new MentalHealth("./data/duke.txt").run();
    }
    public void run() {
        ui.greeting();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MentalHealthException e) {
                ui.formatMessage(e.getMessage());
            }
        }
        scanner.close();
        ui.goodbye();
    }

}
