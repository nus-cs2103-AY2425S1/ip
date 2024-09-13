import java.io.IOException;
import java.text.ParseException;

public class Espresso {
    public static void main(String[] args) throws InvalidCommandException, ParseException {
        Ui ui = new Ui();
        ui.printWelcome();

        Storage storage = new Storage("./data/Espresso.txt");
        TaskList taskList = new TaskList();

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("Error loading tasks from file: " + e.getMessage());
        } catch (ParseException e) {
            ui.printError("Error in parsing tasks " + e.getMessage());
        } catch (InvalidCommandException e) {
            ui.printError("Erratic Command: " + e.getMessage());
        }

        while (true) {
            String input = ui.readCommand();
            if (input.equals("bye")) {
                break;
            }
            try {
                Parser.parse(input, taskList, ui);
            } catch (InvalidCommandException e) {
                ui.printError(e.getMessage());
            }
        }

        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            ui.printError("Cannot save file due to error :  " + e.getMessage());
        }

        ui.printGoodbye();
    }
}
