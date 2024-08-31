import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Edith {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Edith(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (EdithException e) {
            ui.showErrorMessage(e.getMessage() + " Starting with an empty list.");
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while loading saved task list. Starting with an empty list.");
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    public void run() {
        ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            ui.showPrompt();
            userInput = scanner.nextLine();

            try {
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (DateTimeParseException e) {
                ui.showErrorMessage(ui.invalidDateTimeError());
            } catch (EdithException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Edith("./data/edith.txt").run();
    }
}