import java.util.Scanner;

public class Sage {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public Sage(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showMessage("Unable to load tasks. Starting with an empty list");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SageException e) {
                ui.showMessage(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Sage("./data/sage.txt").run();
    }
}
