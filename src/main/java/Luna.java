import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Luna {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luna() {
        this.storage = new Storage();
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (LunaException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        System.out.println("Hello! I'm Luna\n" + "What can I do for you?");

        boolean isRunning = true;

        while (isRunning) {

            try {
                String input = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(input);
                command.execute(tasks, storage);
                if (command instanceof ExitCommand) {
                    isRunning = false;
                }
            } catch (LunaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Luna().run();
    }
}
