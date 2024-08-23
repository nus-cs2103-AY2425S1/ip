import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class BingBongBot {
    private final BingBongUI ui;
    private final Storage storage;
    private TaskList tasks;

    public BingBongBot(BingBongUI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (BingBongException e) {
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BingBongException e) {
                ui.showResponse(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showResponse("Invalid task number. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                ui.showResponse("Task number is out of range. Please enter a valid task number.");
            } catch (Exception e) {
                ui.showResponse("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
