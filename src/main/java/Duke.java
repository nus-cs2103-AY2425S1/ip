import duke.exceptions.MissingDateException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.InvalidInputException;
import duke.tasks.TaskList;
import duke.parsers.Parser;
import duke.ui.Ui;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        taskList = TaskList.init(ui);
        parser = new Parser(taskList, ui);
    }

    public void run() {
        ui.printGreeting();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                if (parser.parse(fullCommand)) {
                    break;
                }
            } catch (InvalidInputException | MissingTaskNameException |
                     MissingDateException | TaskNotFoundException |
                     InvalidDateException e) {
                ui.printMessage(e.toString());
            }
        }
        ui.close();
        ui.printGoodbye();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

