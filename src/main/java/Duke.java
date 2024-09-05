import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingDateException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.TaskNotFoundException;
import duke.parsers.Parser;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The Duke class represents the main entry point for the Duke application.
 * It handles the initialization of the user interface, task list, and parser,
 * and processes user commands in a loop until the program is terminated.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Duke instance. Initializes the task list, user interface, and parser.
     */
    public Duke() {
        ui = new Ui();
        taskList = TaskList.init(ui);
        parser = new Parser(taskList, ui);
    }

    /**
     * Starts the Duke application. Displays a greeting, and enters a command processing loop.
     * The loop continues until the user provides an exit command.
     */
    public void run() {
        ui.printGreeting();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                if (parser.parse(fullCommand)) {
                    break;
                }
            } catch (InvalidInputException | MissingTaskNameException | MissingDateException
                     | TaskNotFoundException | InvalidDateException e) {
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

